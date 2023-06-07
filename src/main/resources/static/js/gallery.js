// -------------------------------------- 새글쓰기
const writebtn = document.querySelector('#writebtn');

// 이미지 첨부 조건 검사
const checkAttachs = () => {

    return false;
};

writebtn?.addEventListener('click', () => {
    const galfrm = document.forms.galfrm;
    if(galfrm.title.value === '')    alert("제목을 입력하세요");
    else if(galfrm.content.value === '') alert("본문을 입력하세요");
    else if(!checkAttachs()) alert("이미지 첨부 조건 불만족!");
    else if(grecaptcha.getResponse() === '') alert("자동가입방지 필수!");
    else{
        galfrm.method='post';
        galfrm.enctype ='multipart/form-data';
        galfrm.submit();
    }
})

// -------------------------------------- 자료실 리스트
const newbtn = document.querySelector('#newbtn');   // 새글쓰기 버튼
newbtn?.addEventListener('click', () => {
    location.href="/gallery/write";
})


// view
const listbtn = document.querySelector('#listbtn');
listbtn?.addEventListener('click', () => {
    location.href = "/pds/list?cpg=1";
})

