export function getCurrentDate(){
	var d = new Date();
	var year = d.getFullYear();
	var month = d.getMonth();
	var day = d.getDate();
	let date = year + '-' + month +'-' + day;
	return date;
}
export function getCurrentHour(){
	var d = new Date();
	return d.getHours();
}
export function Toast(title){
	uni.showToast({
		title:title,
		icon:'none',
		mask:true,
		duration:1000
	})
}
// 自动判卷
export function getScore(obj){
	let score = 0;
	let wrongList = [];
	let j = 0;
	for(var i=0;i<obj.length;i++){
		if(obj[i].answer === obj[i].userAnswer){
			score ++;
		}else{
			wrongList[j++] = (obj[i].id)
		}
	}
	// console.log("题目长度:"+obj.length);
	// console.log(score)
	// 计算分数取整
	let result = {
		wrongList:wrongList,
		score:parseInt((score/obj.length)*100)
	}
	// return parseInt((score/obj.length)*100);
	return result;
}

//判断学生是否登陆
export function isLogin(){
	let user = uni.getStorageSync('user');
	// console.log(user);
	if(user == null || user == '' ){
		return false
	}else{
		return true
	}
}