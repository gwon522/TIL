# TIL - What i Learn Today📖

## index
    start Date : 2021/11/11
* [2021/11/11 : Redux-action](#redux-action) 
* [2021/11/12 : immer.js ](#immer)
* [2021/11/15 : Redux-middleware](#redux-middleware) & [redux-thunk](#redux-thunk) & [redux-saga](#redux-saga) saga 추가 필요

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

## redux-middleware
* 주로 비동기 작업을 처리 할 때 사용
* action을 dispatch 했을 때 reducer를 실행하기 전에 추가 작업을 실행 할 수 있게 해준다.
* redux-thunk와 redux-saga 두가지가 가장 자주 사용된다.
* 미들웨어를 적용 할 때는 applyMiddleware(미들웨어) 함수를 사용한다.    
(logger를 사용 할 때는 맨뒤에 logger를 적용시켜야함 example : applayMiddleware(thunk,logger) )
* 미들웨어를 사용할 경우 리덕스의 흐름 : 액션 -> 미들웨어 -> 리듀서 -> 스토어

### 가능한 추가 작업
 * 액션이 무시되게 만들 수 있다.   
 * 액션을 콘솔에 출력하거나 서버에 로깅 할 수 있다.   
 * 액션이 디스패치 됐을 때 수정해서 리듀서에 전달 할 수 있다.   
 * 특정 액션이 발생했을 때 다른 액션이 발생 할 수 있다.   
 * 특정액션이 발생했을 때 자바스크립트 함수를 실행시킬 수 있다.   

### 리덕스 미들웨어의 템플릿
```javascript
const middleware = store => next => action =>{
    //수행할 작업
}
// 위의 메서드를 풀어서 쓴것
function middleware(store){
    return function(next){
        return function(action){
            수행할 작업
        }
    }
}
```
>* store는 리덕스 스토어 인스턴스( dispatch, getState, subscribe 내장함수가 존재함)이다.   
>* action은 현재 처리하고 있는 액션 객체   
>* next는 액션을 다음 미들웨어에 전달하는 함수 next(action) 같은 식으로 사용    
>  (next를 호출하지 않는다면 액션이 무시처리되어 리듀서로 전달되지 않음)   


## redux-thunk
* 비동기 작업을 처리 할 때 가장 많이 사용하는 미들웨어
* *액션 객체가 아닌 함수를 디스패치 할 수 있다.*
* 함수를 디스패치 할 때는 dispatch와 getState를 파라미터로 받아와야 한다.   
(getState를 사용하지 않을 경우 받아오지 않아도 무관함)

### Thunk 예제
```javascript
export const increase = () => (dispatch, getState) =>{ 
    setTimeOut(()=>dispatch(increase()),1000); //1초 딜레이후 dispatch시킴
}

// getState는 사용하지 않아서 넣지 않아도 괜찮음
export const increase = () => dispatch =>{ 
    setTimeOut(()=>dispatch(increase()),1000); 
}

// Async/await도 적용가능하다
export const callApi = () => async dispatch =>{ 
    try{
        const data = await API.getData();
        dispatch({type : GET_DATA_SUCCESS, data}); 
    }catch(e){
        dispatch({type : GET_DATA_ERROR, error : e});
    }
}
```

## redux-saga
* 액션을 모니터링하다 특정 액션이 발생하면 특정 작업을 하는 방식(특정액션 : 자바스크립트 실행, 다른액션 디스패치, 현재 상태 불러오기)
* Generator 문법을 사용한다.
* 비동기 작업을 할 때 기존 요청을 취소 처리 할 수 있다.   
* 특정 액션이 발생했을 때 다른 액션이 디스패치되게 하거나, js코드를 실행 할 수 있다.   
* 웹소켓 사용 시 Channel이라는 기능을 사용해 효율적으로 코드 관리 가능
* API 요청이 실패했을 때 재요청하는 작업 가능

> ### Generator문법
>* 함수를 작성 할 때 함수를 특정 구간에 멈추거나 되돌아가게 할 수 잇다.   
>* *function* * 이라는 키워드를 사용해 메서드를 작성한다    
>* *yield* 라는 키워드를 사용해 일시적으로 정지되고 메서드.next() 함수를 사용해 다시 시작 될 수 있다.

```javascript
function* generatorFunction(){
    console.l('1');
    yield 1;
    console.l('2');
    yield 2;
    console.l('3');
    yield 3;
    return 4;
}

const generator = generatorFunction();

//next를 호출하게 되면 yield 값을 반환하고 코드의 진행을 멈춘다.
generator.next(); // value : 1, done : false
generator.next(); // value : 2, done : false
generator.next(); // value : 3, done : false
//yield가 끝나고 return이 되면 done 값이 true로 반환된다.
generator.next(); // value : 4, done : true

function* generatorFunction2(){
    let a = yield;
    let b = yield;
    yield a+b;
}

const generator2 = generatorFunction2();

//next호출시 인자를 전달하여 함수 내부에서도 이용 가능하다.
generator2.next(1); // value : undefined, done : false
generator2.next(2); // value : undefined, done : false
generator2.next(); // value : 3, done : true
```
내용추가필요


[목차로 돌아가기](#index)

---