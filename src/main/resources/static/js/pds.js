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


// view
const listbtn = document.querySelector('#listbtn');
listbtn?.addEventListener('click', () => {
    location.href = "/pds/list?cpg=1";
})

// ------------------------------------- 댓글 작성
const rpnewbtn = document.querySelector('#rpnewbtn');
rpnewbtn?.addEventListener('click', () => {
    const rpfrm = document.forms.rpfrm;
    if(rpfrm.reply.value == "")alert("내용을 입력하세요");
    else{
        rpfrm.method = 'post';
        rpfrm.action = '/pds/replyok?pno=' + rpfrm.pno.value;
        rpfrm.submit();
    }
})