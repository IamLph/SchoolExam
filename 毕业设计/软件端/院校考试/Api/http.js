const host = "http://127.0.0.1:8082/api";

export function http(url, method, params) {
	// let token = 'token'
	// 封装Token
	let token = uni.getStorageSync('token')
	if(token == ''){
		token = 'token'
	}
	// console.log(token)
	let sign = 'sign'
	let data = {
		token,
		sign
	}
  // 判断是否有发送数据
  if (params.data) {
    for (let key in params.data) {
      if (params.data[key] == null || params.data[key] == 'null') {
        delete params.data[key]
      }
    }
    data = { ...data, ...params.data }
  }
  
  let type = 'application/x-www-form-urlencoded';
  if(data.type == 'json'){
	  type = 'application/json'
  }
  uni.request({
    url: host + url,
    method: method,
    data,
    header: {
      'content-type': type,
      'token':data.token
    }, 
    success(res) {
      params.success && params.success(res.data)
    },
    fail(err) {
      params.fail && params.fail(err)
    }
  })
}
export default {
  host
}