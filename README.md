# TIL - What i Learn Today📖

## index
    start Date : 2021/11/11
* [2021/11/11 : Redux-action](#redux-action) 😀
* [2021/11/12 : immer.js ](#immer)

---
## redux-action
* redux-action은 액션생성 함수와 리듀서를 간결하게 작성할 수 있게 해준다.
- createAction, handleAction, combineAction 이 있다.(createAction과 handleAction 을 주로 이용하는거 같다.)

## *1. createAction*
    * 액션 생성 함수를 만들어주는 함수 이다.
    * 직접 객체를 만들 필요가 없음
    * 파라미터로 전달 받은 값을 액션의 payload 값으로 설정해준다. 
```javascript
// 기존
export const increment = (index) => ({
    type: 'INCREMENT',
    index
});
//===========================================
// createAction를 사용
export const increment = createAction('INCREMENT');

//이렇게 createAction로 생성 후 increment(파라미터) 를 넣어서 사용한다.
increment(1)
```
### createAction으로 생성한 increment를 호출 하게 되면 이런식으로 액션이 만들어진다.  
```javascript
{
    type: 'INCREMENT',
    payload: 1
}
```
## *2. handleAction*
    * 리듀서 함수를 만들어주는 함수이다.
    * 리듀서를 간단하고 가독성있게 작성 할 수 있다. 
    * 첫번째 인자는 액션을 실행할 함수를 가진 객체, 두번째 인자는 기본값을 넣어준다. 
### 기존 형식
```javascript
const reducer = (state = initState, action) => { 
    switch (action.type) { 
        case 'INCREMENT': 
            return { 
                ...state, 
                number : state.number + 1 
            } 
        case 'DECREMENT': 
            return { 
                ...state, 
                number : state.number - 1
            } 
        default: 
            return state
    } 
}
```
### creahandleAction을 사용
```javascript
const reducer = handleActions({
  'INCREMENT': (state, action) => ({
    counter: state.counter + action.payload
  }),

  'DECREMENT': (state, action) => ({
    counter: state.counter - action.payload
  })
}, { counter: 0 });
```

[목차로 돌아가기](#index)
   
   ---

## immer
* React에서 state의 불변성을 유지하는 코드를 쉽게 작성하게 해주는 라이브러리   
> 변하지 않는 성질을 불변성이라고 한다.    
> React에서는 State를 직접적으로 변화시키면 안되기 때문에 불변성을 유지하는것이 중요하다.   
#### React의 State
* 데이터의 관리소라고 보면 될 것 같다.
* State의 얕은비교를 통해서 새로운 값인지 판단하게 된다.
* 새로운 값일 경우 리렌더링
* state를 변화시키고 DOM을 다시만들기 위해서는 새로운 객체 or 새로운 배열을 만들어 새로운 참조값이 생성되어야 한다.

```javascript
//이렇게 직접적으로 수정하면 리렌더링 되지 않는다.
state.push('value') //새로운 참조값이 아님

//state 변화하는 경우
//Class component 일경우
setState(prevState =>{
    return newState;
})
//혹은
setState(newState)

//functional component 일 경우
const [value, setValue] = useState(초기값);
setValue(newState);
``` 

### immer를 사용
```javascript
// state : 바꾸고싶은 객체 or 배열
// draft : 어떤식으로 변경할지에 대한 함수
const nextState = produce(state, draft =>{
    draft.value = value;
})
``` 
이런식으로 변경하게 되면 얕은복사를 통해서 리렌더링이 된다.   
### redux에서 immer 사용
* 주로 리듀서에서 사용된다.
```javascript
//기존 리듀서 생성할때
const reducer = (state = initState, action) => { 
    switch (action.type) { 
        case 'INCREMENT': 
            return { 
                ...state, 
                number : state.number + 1 
            } 
        case 'DECREMENT': 
            return { 
                ...state, 
                number : state.number - 1
            } 
        default: 
            return state
    } 
}
// immer를 적용한 리듀서 
const reducer = (state = initState, action) => { 
    switch (action.type) { 
        case 'INCREMENT': 
            return produce(state,draft=>{
                draft.number+1;
            })
        case 'DECREMENT': 
            return produce(state,draft=>{
                draft.number-1;
            })
        default: 
            return state
    } 
}
``` 
스프레드 문법을 사용하지 않고 깔끔하게 코드가 변경되었다.   

### useState 함수형 업데이트에 사용할 경우
```javascript
const [value,setValue] = useState({
    number : 1,
    done : false
});

//기존
const onClick = useCallback(()=>{
    setValue(value =>({
        ...value,
        done : !value.done
    }));
},[]);

//적용한것
const onClick = useCallback(()=>{
    setValue(
        produce(draft =>{
            draft.done = !draft.done;
        })
    );
},[]);
``` 
첫번째 파라미터인 상태값을 생략하고 두번째 파라미터인 업데이트 함수만 넣어주면 업데이트를 해주는 함수가 된다.

[목차로 돌아가기](#index)

---