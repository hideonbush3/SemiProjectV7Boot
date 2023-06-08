// -------------------------------------- 새글쓰기
const writebtn = document.querySelector('#writebtn');

// 이미지 첨부 조건 검사
const checkAttachs = () => {
    const attachs = document.querySelector('#attachs')
    let checkOk = true;
    // 이미지 첨부파일이 하나 이상일때
    if('files' in attachs && attachs.files.length > 0){
        // 첨부파일들이 이미지인지 검사
        for(attach of attachs.files){
            // console.log(attach.name + ',' + attach.type
            //     + ',' + attach.size)
            // 이미지 파일의 MIME 형식 : image/jpg, image/jpeg, image/png, image/gif
            if(!attach.type.startsWith("image")){
                alert("첨부파일중 이미지 파일이 아닌 파일이 존재합니다");
                checkOk =  false;
            }
        }
    }else{
        alert("하나 이상의 이미지를 선택하세요");
        checkOk = false;
    }

    return checkOk;
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

