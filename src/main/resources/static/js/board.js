let findbtn = document.querySelector('#findbtn');
let findtype = document.querySelector('#findtype');
let findkey = document.querySelector('#findkey');
let newbtn = document.querySelector('#newbtn'); // list 페이지의 새글쓰기 버튼

findbtn?.addEventListener('click', () => {
    if(findkey.value === '') {
        alert("검색어를 입력하세요");
        return;
    }
    let query = `/board/find?ftype=${findtype.value}&fkey=${findkey.value}&cpg=1`;
    location.href = query;
});

newbtn?.addEventListener('click', () => {
    location.href = "/board/write";
})

// -------------------------------------- write
let bdfrm = document.forms.bdfrm;
let writebtn = document.querySelector('#writebtn');
let cancelbtn = document.querySelector('#cancelbtn');

writebtn?.addEventListener('click', () => {
    if(bdfrm.title.value === '')    alert("제목을 입력하세요");
    else if(bdfrm.content.value === '') alert("본문을 입력하세요");
    else if(grecaptcha.getResponse() === '') alert("자동가입방지를 체크하세요");
    else{
        bdfrm.method='post';
        bdfrm.submit();
    }
})

const prevbtn = document.querySelector("#prevbtn");
const nextbtn = document.querySelector("#nextbtn");
const listbtn = document.querySelector("#listbtn");
prevbtn?.addEventListener('click', () => {});

nextbtn?.addEventListener('click', () => {});

listbtn?.addEventListener('click', () => {
    location.href = '/board/list?cpg=1'; });

