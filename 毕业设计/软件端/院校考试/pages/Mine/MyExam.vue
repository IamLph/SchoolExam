<template>
	<!-- 已经参与的考试 -->
	<view>
		<exam :partExam="partExam"></exam>
	</view>
</template>

<script>
	import exam from '../component/ExamCP.vue'
	// 模拟数据
	// import { partExam } from '../../data/AllData.js'
	import { getCurrentDate } from '../../method/MethodApi.js'
	import * as api from '../../Api/api.js'
	export default {
		data() {
			return {
				partExam:null,
			}
		},
		onLoad() {
			let userId = uni.getStorageSync('user').userId;
			this.getExamHistory(userId)
		},
		components:{
			exam
		},
		methods: {
			getExamHistory(userId){
				let that = this
				api.getExamHistory({
					data:{
						userId:userId
					},success:res=>{
						console.log(res)
						let a = res.data[0].startTime;
						console.log(a)
						let b = getCurrentDate(a)
						console.log(b)
						if(res.status == 200){
							let data = res.data;
							let newObj = [];
							for(var i=0;i<data.length;i++){
								let state = '--';
								let type = '改卷中'
								let time = getCurrentDate(data[i].startTime)
								if(data[i].publish == '1'){
									state = data[i].finalscore,
									type = '已完成'
								}
								newObj[i] = {
									"state":state,
									"time":time,
									"title":data[i].type,
									"type":type,
									"imgUrl":data[i].classImgUrl
								}
							}
							that.partExam = newObj
						}else{
							uni.showToast({
								title:"还没有考试记录",
								icon:'none'
							})
						}
					},fail:err=>{
						uni.showToast({
							title:"网络延迟",
							icon:"none"
						})
					}
				})
			}
		}
	}
</script>

<style>
	page{
		background-color: white;
	}
</style>
