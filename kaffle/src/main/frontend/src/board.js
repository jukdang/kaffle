import './board.css'
import { useState, useEffect, useRef } from 'react';

function Board() {
    
    //const [hello, setHello] = useState('');
    // useEffect(() => {
    //     axios.get('/api/hello')
    //     .then(response => setHello(response.data))
    //     .catch(error => console.log(error))
    // }, []);

    const answerList = [
        ['a', 'b', 'c', 'd', 'e'],
        ['l', 'm', 'n', 'o', 'j'],
        ['f', 'g', 'h', 'i', 'k'],
        ['p', 'q', 'r', 's', 't'],
        ['y', 'x', 'w', 'v', 'u']
    ]


    const [isSelected, setIsSelected] = useState(false);
    const [selected, setSelected] = useState(null);

    function checkTile(tile){
        let tile_value = tile.textContent;
        let data_pos = JSON.parse(tile.getAttribute("data-pos"));
        data_pos= Object.values(data_pos);
        console.log(data_pos[0], data_pos[1])
        console.log(answerList[data_pos[0]][data_pos[1]])
        console.log(tile_value)
        if(tile_value === answerList[data_pos[0]][data_pos[1]].toUpperCase()){
            tile.style.backgroundColor = 'blue';
            // tile.disabled = true;
        }
        
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

    useEffect(() => {
        setTimeout(() => {
            let alertbar = document.getElementsByClassName('alertbar')[0];
            alertbar.textContent = 'Start!'
            
            setTimeout(() => {
                let elementList = document.getElementsByClassName('tile');
                for(let element of elementList){
                    checkTile(element);
                }

                alertbar.style.display = 'None';
                let board = document.getElementsByClassName('board')[0];
                board.style.opacity = '100%';
                
                return () => {
                    console.log('start game');
                };
                }, 300
            )
            
        }, 500);
      }, []);

    function board_tile(size){
        let arr = [];
        let char = 65;
        for(let i=0;i<size;i++){
            for(let j=0;j<size;j++){
                let data_pos = '{"x":'+i+',"y":'+j+'}';
                arr.push(
                    <div 
                        className='tile'
                        onClick={onClicked}
                        data-pos={data_pos}
                        id={char}
                        key={char}>
                        {String.fromCharCode(char)}
                    </div>
                )
                char++;
            }
        }

        return arr;
    }


    return(
        <div className='mainGame'>
            <div className='board'>
                {board_tile(5)}
            </div>
            <div className='alertbar'>Ready</div>
        </div>
    )
}

export default Board;