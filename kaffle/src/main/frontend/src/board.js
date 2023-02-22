import './board.css'
import { useState, useEffect } from 'react';
import axios from 'axios';
import $ from 'jquery';

function Board() {

    const answerList = [
        ['ㄱ', 'ㅖ', 'ㅅ', 'ㅏ', 'ㄴ'],
        ['ㅣ', '-', 'ㅏ', '-', 'ㅐ'],
        ['ㅇ', 'ㅏ', 'ㅇ', 'ㅑ', 'ㅇ'],
        ['ㅓ', '-', 'ㅛ', '-', 'ㅛ'],
        ['ㄱ', 'ㅛ', 'ㅇ', 'ㅑ', 'ㅇ']
    ]
    // const wordList = ['ㄱ', 'ㅐ', 'ㅇ', 'ㅛ', 'ㅇ', 'ㄴ', 'ㅅ', 'ㅏ', 'ㅖ', 'ㅏ', 'ㅣ', 'ㅇ', 'ㅏ', 'ㅇ', 'ㅑ', 'ㄱ', 'ㅛ', 'ㅇ', 'ㅑ', 'ㅓ', 'ㅛ'];
    // let wordList = [
    //     'ㄱ', 'ㅖ', 'ㅅ', 'ㅏ', 'ㄴ',
    //     'ㅣ', 'ㅏ', 'ㅐ',
    //     'ㅇ', 'ㅏ', 'ㅇ', 'ㅑ', 'ㅇ',
    //     'ㅓ', 'ㅛ', 'ㅛ',
    //     'ㄱ', 'ㅇ', 'ㅛ', 'ㅇ', 'ㅑ'
    // ];
    const rowList = [
        ['ㄱ', 'ㅖ', 'ㅅ', 'ㅏ', 'ㄴ'],
        ['-', '-', '-', '-', '-'],
        ['ㅇ', 'ㅏ', 'ㅇ', 'ㅑ', 'ㅇ'],
        ['-', '-', '-', '-', '-'],
        ['ㄱ', 'ㅛ', 'ㅇ', 'ㅑ', 'ㅇ']
    ];
    const columnList = [
        ['ㄱ', 'ㅣ', 'ㅇ', 'ㅓ', 'ㄱ'],
        ['-', '-', '-', '-', '-'],
        ['ㅅ', 'ㅏ', 'ㅇ', 'ㅛ', 'ㅇ'],
        ['-', '-', '-', '-', '-'],
        ['ㄴ', 'ㅐ', 'ㅇ', 'ㅛ', 'ㅇ']
    ];

    const [wordList, setWordList] = useState([]);
    const [boardSize, setBoardSize] = useState(5); 
    const [isSelected, setIsSelected] = useState(false);
    const [selected, setSelected] = useState(null);
    const [correctCnt, setCorrectCnt] = useState(0);
    const [tryCnt, setTryCnt] = useState(15);
    const [isGameEnded, setIsGameEnded] = useState(false);

    useEffect(() => {
        setBoardSize(5);
        setCorrectCnt(0);
        setTryCnt(4);

        (async () => {
            await axios.get('/testJamo')
            .then(response => {
                const res = response.data;
                console.log('response: ', res)
                setWordList((res.row1 +
                    res.col1[1] + res.col2[1] + res.col3[1] +
                    res.row2 +
                    res.col1[3] + res.col2[3] + res.col3[3] +
                    res.row3).split(''));
            })
            .catch(error => {
                console.log(error)
                setWordList([
                    'ㄱ', 'ㅖ', 'ㅅ', 'ㅏ', 'ㄴ',
                    'ㅣ', 'ㅏ', 'ㅐ',
                    'ㅇ', 'ㅏ', 'ㅇ', 'ㅑ', 'ㅇ',
                    'ㅓ', 'ㅛ', 'ㅛ',
                    'ㄱ', 'ㅇ', 'ㅛ', 'ㅇ', 'ㅑ'
                ]);
            })
        })();


        let cnt=1;
        for(let i=0;i<boardSize;i++){
            for(let j=0;j<boardSize;j++){
                if(i*j%2===0){
                    let area = (i+1)+"/"+(j+1)+"/"+(i+1)+"/"+(j+1);
                    $('.tile:nth-child('+cnt+')').css({
                        "grid-area" : area
                    })
                    cnt++;
                }
                
            }
        }
        console.log("finish to set grid-area");
        
        let startBtn = document.getElementsByClassName('start-btn')[0];
        startBtn.classList.remove('out');
    }, []);

    useEffect(() => {
        console.log("check game end - ",correctCnt, boardSize**2 - parseInt(boardSize/2)**2)
        if(correctCnt === boardSize**2 - parseInt(boardSize/2)**2){
            console.log('Game End');
            gameEnd(true);
        }
        else if(tryCnt === 0){
            gameEnd(false);
        }
    },[correctCnt, tryCnt])

    function startGame(){
        let startBtn = document.getElementsByClassName('start-btn')[0];
        let alertbar = document.getElementsByClassName('alertbar')[0];

        startBtn.classList.add('out'); 
        alertbar.textContent = 'Start!'
        
        
        setTimeout(() => {
            let elementList = document.getElementsByClassName('tile');
            for(let element of elementList){
                checkTile(element);
            }
            console.log('finish to check tile correct');

            alertbar.classList.add('out');
            let board = document.getElementsByClassName('board')[0];
            board.style.opacity = '100%';
        }, 500)
    }

    function checkTile(tile){
        let tile_value = tile.textContent.toLowerCase();
        let data_pos = JSON.parse(tile.getAttribute("data-pos"));
        let x = data_pos.x;
        let y = data_pos.y;
        // console.log(data_pos[0], data_pos[1])
        // console.log(answerList[data_pos[0]][data_pos[1]])
        // console.log(tile_value)
        // console.log(rowList[x])
        // console.log(columnList[y])
        // console.log(rowList[x].includes(tile_value), columnList[y].includes(tile_value))
        
        tile.classList.remove('in-line');
        if(tile_value === answerList[x][y]){
            // console.log('correct');
            tile.classList.add('correct');
            setCorrectCnt(correctCnt => correctCnt+1);
            
        }
        else if(rowList[x].includes(tile_value) || columnList[y].includes(tile_value)){
            tile.classList.add('in-line');
        }
    }

    function gameEnd(isClear){
        console.log('--------------END----------=', isClear);
        setIsGameEnded(true);

        let board = document.getElementsByClassName('board')[0];
        let alertbar = document.getElementsByClassName('alertbar')[0];
        
        let closeBtn = document.getElementsByClassName('end-btn')[0];
        

        board.style.opacity = '90%';
        if(isClear){
            board.style.background = '#DFFEBB';
            alertbar.textContent = 'Good!'
        }
        else{
            board.style.background = '#FEC6C6';
            alertbar.textContent = 'try next!'
        }
        alertbar.classList.remove('out');
        closeBtn.classList.remove('out');
        
    }

    function showGameResult(){
        let board = document.getElementsByClassName('board')[0];
        let alertbar = document.getElementsByClassName('alertbar')[0];
        let closeBtn = document.getElementsByClassName('end-btn')[0];
        let explanationTable = document.getElementsByClassName('explanation')[0];

        board.style.opacity = '100%';
        alertbar.classList.add('out');
        closeBtn.classList.add('out');
        explanationTable.classList.remove('out');

    }

    const onClicked = (e) => {
        const selected2 = e.target;
        if(isSelected){
            if(selected2.id === selected.id){
                setIsSelected(false);
                setSelected(null);
                selected2.classList.toggle('selected');
            }
            else{
                setIsSelected(false);
                setSelected(null);
                selected.classList.toggle('selected');

                let swapword = selected.textContent;
                selected.textContent = selected2.textContent;
                selected2.textContent = swapword;

                checkTile(selected);
                checkTile(selected2);
                setTryCnt(tryCnt - 1);
            }
        }
        else{
            setIsSelected(true);
            setSelected(e.target);
            selected2.classList.toggle('selected');
        }
    }

    function board_tile(){

        
        
        let arr = [];
        let word = 0;
        for(let i=0;i<boardSize;i++){
            for(let j=0;j<boardSize;j++){
                if(i*j%2===0){
                    let data_pos = '{"x":'+i+',"y":'+j+'}';
                    arr.push(
                        <div 
                            className='tile'
                            onClick={onClicked}
                            data-pos={data_pos}
                            id={word}
                            key={word}>
                            {wordList[word]}
                        </div>
                    )
                    word++;
                }
            }
        }

        return arr;
    }


    return(
        <div className='main'>
            <div className='mainGame'>
                <div className='board'>
                    {board_tile()}
                </div>
                <div className='alertbar'>
                    Ready
                </div>
                <div className='next-btn start-btn out' onClick={startGame}>
                    start
                </div>
                <div className='next-btn end-btn out' onClick={showGameResult}>
                    OK
                </div>
            </div>
            <div className='leftmove'> Left move : {tryCnt}</div>
            <div className='explanation out'>
                <p>Answer</p>
                <table className="explanation-table">
                    <tbody>
                        <tr>
                            <th>계산</th>
                            <td>
                                <p>1. 명사: 수를 헤아림.</p>
                                <p>2. 명사: 어떤 일을 예상하거나 고려함.</p>
                                <p>3. 명사: 값을 치름.</p>
                            </td>
                        </tr>
                        <tr>
                            <th>아양</th>
                            <td>
                                <p>1. 명사: 귀염을 받으려고 알랑거리는 말. 또는 그런 짓.</p>
                            </td>
                        </tr>
                        <tr>
                            <th>교양</th>
                            <td>
                                <p>1. 명사: 가르치어 기름.</p>
                                <p>2. 명사: 학문, 지식, 사회생활을 바탕으로 이루어지는 품위. 또는 문화에 대한 폭넓은 지식.</p>
                            </td>
                        </tr>
                        <tr>
                            <th>기억</th>
                            <td>
                                <p>1. 명사: 이전의 인상이나 경험을 의식 속에 간직하거나 도로 생각해 냄.</p>
                                <p>2. 명사: 심리 사물이나 사상(事象)에 대한 정보를 마음속에 받아들이고 저장하고 인출하는 정신 기능.</p>
                                <p>3. 명사: 정보·통신 계산에 필요한 정보를 필요한 시간만큼 수용하여 두는 기능.</p>
                            </td>
                        </tr>
                        <tr>
                            <th>사용</th>
                            <td>
                                <p>1. 명사: 일정한 목적이나 기능에 맞게 씀.</p>
                                <p>2. 명사: 사람을 다루어 이용함.</p>
                            </td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td>
                                <p>1. 명사: 그릇이나 포장 따위의 안에 든 것.</p>
                                <p>2. 명사: 사물의 속내를 이루는 것.</p>
                                <p>3. 명사: 말, 글, 그림, 연출 따위의 모든 표현 매체 속에 들어 있는 것. 또는 그런 것들로 전하고자 하는 것.</p>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        
    )
}

export default Board;