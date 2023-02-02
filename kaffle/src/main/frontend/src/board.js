import './board.css'
import { useState, useEffect, useRef } from 'react';
import $ from 'jquery';

function Board() {
    
    //const [hello, setHello] = useState('');
    // useEffect(() => {
    //     axios.get('/api/hello')
    //     .then(response => setHello(response.data))
    //     .catch(error => console.log(error))
    // }, []);

    const answerList = [
        ['g', 'a', 'u', 'z', 'e'],
        ['r', '-', 'p', '-', 'r'],
        ['a', 'm', 'p', 'l', 'e'],
        ['v', '-', 'e', '-', 'c'],
        ['e', 'g', 'r', 'e', 't']
    ]
    const wordList = ['G', 'P', 'P', 'A', 'E', 'R', 'E', 'Z', 'C', 'U', 'M', 'R', 'R', 'E', 'E', 'A', 'L', 'T', 'G', 'V', 'E'];
    const rowList = [
        ['g', 'a', 'u', 'z', 'e'],
        ['-', '-', '-', '-', '-'],
        ['a', 'm', 'p', 'l', 'e'],
        ['-', '-', '-', '-', '-'],
        ['e', 'g', 'r', 'e', 't']
    ];
    const columnList = [
        ['g', 'r', 'a', 'v', 'e'],
        ['-', '-', '-', '-', '-'],
        ['u', 'p', 'p', 'e', 'r'],
        ['-', '-', '-', '-', '-'],
        ['e', 'r', 'e', 'c', 't']
    ];

    const [boardSize, setBoardSize] = useState(5); 
    const [isSelected, setIsSelected] = useState(false);
    const [selected, setSelected] = useState(null);
    const [correctCnt, setCorrectCnt] = useState(0);

    useEffect(() => {
        setCorrectCnt(0);

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
            
        setTimeout(() => {
            let alertbar = document.getElementsByClassName('alertbar')[0];
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
            }, 300)
        }, 500);
    }, []);

    useEffect(() => {
        console.log(correctCnt)
        if(correctCnt == boardSize**2 - parseInt(boardSize/2)**2){
            gameEnd();
        }
    },[correctCnt])

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
            tile.classList.toggle('correct');
            setCorrectCnt(correctCnt => correctCnt+1);
            
        }
        else if(rowList[x].includes(tile_value) || columnList[y].includes(tile_value)){
            tile.classList.add('in-line');
        }
    }
    function gameEnd(){
        let board = document.getElementsByClassName('board')[0];
        board.style.opacity = '50%';
        
        let alertbar = document.getElementsByClassName('alertbar')[0];
        alertbar.textContent = 'Good!'
        alertbar.classList.remove('out');
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
        <div className='mainGame'>
            <div className='board'>
                {board_tile()}
            </div>
            <div className='alertbar'>Ready</div>
        </div>
    )
}

export default Board;