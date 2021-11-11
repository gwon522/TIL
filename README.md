# TIL - What i Learn Today📖

## index
    start Date : 2021/11/11
* [2021/11/11 : Redux-action](#redux-action) 😀
* [ ]

---
## redux-action
* redux-action은 액션생성 함수와 리듀서를 간결하게 작성할 수 있게 해준다.
- createAction, handleAction, combineAction 이 있다.(createAction과 handleAction 을 주로 이용하는거 같다.)

# 1. createAction
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
# 2. handleAction
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

