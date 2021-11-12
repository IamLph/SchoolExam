export function getLoginUser() {
    let user = JSON.parse(localStorage.getItem('loginUser'));
    let type = localStorage.getItem('loginType');
    let obj = {
        user:user,
        type:type
    };
    if(user == null || user === '' || type === '' || type == null){
        return null;
    }else{
        return obj;
    }
}
export function getQuestionVo(data) {
    let objArr = [];
    for(let i=0;i<data.length;i++){
        let json = JSON.parse(data[i].optionList);
        let jsonOptionList = [];
        //解析选项
        for(var j=0;j<json.length;j++){
            jsonOptionList[j] = {
                "prefix": json[j].prefix,
                "content": json[j].content
            }
        }
        let typeInfo = "";
        if(data[i].type === '1'){
            typeInfo = "单选题";
        }else if(data[i].type === '2'){
            typeInfo = "多选题";
        }else if(data[i].type === '3'){
            typeInfo = "判断题";
        }
        let obj = {
            "examId":data[i].examId,
            "className":data[i].className,
            "courseId":data[i].courseId,
            "title":data[i].title,
            "type":typeInfo,
            "optionList":jsonOptionList,
            "answer":data[i].answer,
            "explain":data[i].explain,
        };
        objArr[i] = obj;
        // console.log(jsonOptionList);

    }
    return objArr;
}
