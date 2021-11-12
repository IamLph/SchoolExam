import axios from 'axios';
import QS from 'qs'

//请求后台ip地址
export const baseUrl = "http://localhost:8082/api";

axios.defaults.baseURL = baseUrl;
//十秒请求超时
axios.defaults.timeout = 10000;
// axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
//post请求头设置
let ContentType = "application/x-www-form-urlencoded;charset=UTF-8;application/json";
let uploadFileType = "multipart/form-data";
axios.defaults.headers.post['Content-Type'] = ContentType;

//响应拦截
axios.interceptors.response.use(
  response =>{
    if(response.status === 200){
      return Promise.resolve(response);
    } else {
      return Promise.reject(response);
    }
  },
  error => {
    if(error.response.status){
      switch (error.response.status) {
        case 400:
          // alert("操作失败");
          break;
        case 404:
          // alert("未找到");
          break;
        case 500:
          // alert("500");
          break;
        default:
          // alert("服务异常")
      }
      return Promise.reject(error.response);
    }
  }
);
//封装get
export function get(url,params) {
  return new Promise((resolve, reject) =>{
    axios.get(url, {
      params: params
    }).then(res => {
      resolve(res.data);
    }).catch(err =>{
      reject(err.data)
    })
  });
}
//封装post
export function post(url, params) {
  return new Promise((resolve, reject) => {
    axios.post(url,QS.stringify(params)).then(res=>{
      resolve(res.data);
    }).catch(err=>{
      reject(err.data)
    })
  });
}
// export function uploadFile(url, data){
//   let config = {
//     //请求的接口，在请求的时候，如axios.get(url,config);这里的url会覆盖掉config中的url
//     url: url,
//     //基础url前缀
//     baseURL: baseUrl,
//     transformResponse: [function (data1) {
//       let data = data1;
//       if (typeof data1 == "string") {
//         data = JSON.parse(data1);
//       }
//       //这里提前处理返回的数据;
//       if (data.message && (data.data === 'login.invalid.token')) {
//         window.localStorage.removeItem("access-user");
//         alert("超时请重新登陆");
//         window.location.href = '/';
//       }
//       return data;
//     }],
//     //请求头信息
//     headers: {'access-user': window.localStorage.getItem('access-user'), 'Content-Type': uploadFileType},
//
//     //跨域请求时是否需要使用凭证
//     withCredentials: true,
//     // 返回数据类型
//     responseType: 'json', //default
//   };
//   return axios.post(url, data, config);
// }
