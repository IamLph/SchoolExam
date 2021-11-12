<template>
	<!-- 准备进入考试界面 -->
	<view class="Container">
		<view class="TopContainer">
			<view class="top_Left">
				<image :src="user.headImg" class="top_Img"></image>
			</view>
			<view class="top_Right">
				<view class="top_title">
					姓名:<text class="top_text">{{user.name}}</text>
				</view>
				<view class="top_title">
					学号:<text class="top_text">{{user.userId}}</text>
				</view>
				<view class="top_title">
					班级:<text class="top_text">{{user.grade}}</text>
				</view>
				<view class="top_title">
					考试科目:<text class="top_text">{{title}}</text>
				</view>
				<view class="top_title">
					考试日期:<text class="top_text">{{time}}</text>
				</view>
				<view class="top_title">
					考试时间:<text class="top_text">{{detailTime}}</text>
				</view>
			</view>
		</view>
		<view class="bottom_Container">
			<view class="agree_title">
				<checkbox-group @change="checkboxChange">
					<label>
						<checkbox value="cb"/>
					</label>
				</checkbox-group>
				<view class="agree_right">我已阅读并同意<text class="examText" @tap="getRule">《考试规定》</text></view>
			</view>
			<view class="button_View">
				  <button class="button" @click="goTest">开始考试</button>
			</view>
		</view>
	</view>
</template>

<script>
	import { getCurrentDate ,getCurrentHour,Toast } from '../../method/MethodApi.js'
	import * as api from '../../Api/api.js'
	export default {
		data(){
			return{
				examId:0,
				agree:false,
				user:null,
				noticeId:'',
				title:'',
				time:'',
				detailTime:'',
				startTime:'',
				endTime:'',
				teacherId:'',
			}
		},
		onLoad(option){
			let item = JSON.parse(decodeURIComponent(option.item))
			this.noticeId = item.id;
			this.time = item.time;
			this.title = item.type;
			this.detailTime = item.detailTime;
			this.teacherId = item.teacherId;
			// console.log(item)
			// console.log(item.teacherId)
			// 获取当前用户 并设置
			let user = uni.getStorageSync('user');
			this.user = user;
			// console.log(user)
			// 根据试题编号获取考试时间等
			this.getNoticeById(item.id)
		},
		methods:{
			getNoticeById(id){
				let that = this;
				api.getNiticeByNoticeId({
					data:{
						noticeId:id
					},success:res=>{
						// console.log(res)
						if(res.status == 200){
							let currentTime = Date.parse(new Date());
							let startTime = res.data.startTime;
							let endTime = res.data.endTime;
							startTime = new Date(startTime).getTime();
							endTime = new Date(endTime).getTime();
							that.startTime = startTime;
							that.endTime = endTime;
						}
						// console.log(currentTime)
						// console.log(noticeTime)
					},fail:err=>{
						uni.showToast({
							title:"网络延迟",
							icon:"none"
						})
					}
				})
			},
			checkboxChange(e){
				this.agree = !this.agree;
			},
			getRule(){
				// console.log("获取考试规定")
				uni.navigateTo({
					url:"../component/ExamRule"
				})
			},
			goTest(){
				if(this.agree){
					// let dates = getCurrentDate();
					let currentTime = Date.parse(new Date());
					// console.log(currentTime);
					// console.log(this.startTime)
					// console.log(this.endTime)
					if(currentTime > this.startTime && currentTime <= this.endTime){
						// console.log("进行中")
						uni.navigateTo({
							url:'Exam?id='+this.noticeId+"&tid="+this.teacherId
						})
					}else if(currentTime < this.startTime){
						// console.log("还未开始")
						Toast("当前还未达到考试时间");
					}else if(currentTime > this.endTime){
						// console.log("已结束")
						Toast("考试已结束");
					}
					// 判断日期时间等等去进入考试，目前默认可以进入
					// var hour = getCurrentHour();
					// if(hour>5){
					// 	// 可以进入考试了
					// 	// console.log(dates)
					// 	// console.log(hour)
					// 	uni.navigateTo({
					// 		url:'Exam'
					// 	})
					// }else{
					// 	Toast("当前还未达到考试时间");
					// }
					
				}else{
					Toast("请先同意考试协议");
				}
			}
		}
	}
</script>

<style>
	page{
		background-color: rgb(255, 255, 255);
	}
	.Container{
		background-color: rgb(255, 255, 255);
		height: 100%;
		padding: 10rpx 20rpx 30rpx 20rpx;
	}
	.TopContainer{
		height: 400rpx;
		padding: 10rpx;
		display: flex;
	}
	.top_Left{
		width: 40%;
		height: 100%;
		text-align: center;
	}
	.top_Img{
		height: 330rpx;
		width: 230rpx;
		border-radius: 0.2em;
	}
	.top_Right{
		width: 60%;
		height: 100%;
		padding: 20rpx;
	}
	.top_title{
		font-weight: 600;
		color: #282828;
		font-size: 30rpx;
	}
	.top_text{
		margin-left: 20rpx;
		font-weight: 500;
	}
	.bottom_Container{
		height: 100%;
		padding: 20rpx;
	}
	.agree_title{
		display: flex;
		height: 50rpx;
		line-height: 50rpx;
	}
	.examText{
		color: blue;
		text-decoration: underline;
	}
	.agree_right{
		margin-left: 20rpx;
	}
	.button_View{
		margin-top: 50rpx;
	}
	.button{
		background-color: #009045;
		color: white;
		font-size: 40rpx;
	}
</style>
