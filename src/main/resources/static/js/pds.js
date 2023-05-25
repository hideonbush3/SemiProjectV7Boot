// -------------------------------------- 새글쓰기
const writebtn = document.querySelector('#writebtn');
writebtn?.addEventListener('click', () => {
    const pdsfrm = document.forms.pdsfrm;
    if(pdsfrm.title.value === '')    alert("제목을 입력하세요");
    else if(pdsfrm.content.value === '') alert("본문을 입력하세요");
    else if(grecaptcha.getResponse() === '') alert("자갑방 필수!");
    else{
        pdsfrm.method='post';
        pdsfrm.enctype ='multipart/form-data';
        pdsfrm.submit();
    }
})

// -------------------------------------- 자료실 리스트
const newbtn = document.querySelector('#newbtn');   // 새글쓰기 버튼
newbtn?.addEventListener('click', () => {
    location.href="/pds/write";
})
