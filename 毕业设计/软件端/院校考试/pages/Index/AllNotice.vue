<template>
	<Notice :examNotice='examNotice' :all="true"></Notice>
</template>

<script>
	// import {
	// 	examNotice
	// } from '../../data/AllData.js'
	import Notice from '../component/Notice.vue'
	import * as api from '../../Api/api.js'
	export default{
		components:{
		    Notice
		},
		data(){
			return{
				// 考试通知列表
				examNotice: null,
			}
		},
		onLoad() {
			let user = uni.getStorageSync('user');
			let userId = user.userId;
			this.setExam(userId);
		},
		methods:{
			setExam(userId){
				let that = this;
				api.getExamInfoById({
					data:{
						userId:userId
					},success:res=>{
						if(res.status == 200){
							that.examNotice = res.data;
						}
					},fail:err=>{
						uni.showToast({
							title:"网络延迟",
							icon:"none"
						})
					}
				})
			},
		}
	}
</script>

<style>
</style>
