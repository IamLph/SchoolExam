<template>
	<!-- 选择课程练习 -->
	<view class="Container">
		<view class="title">请选择要练习的课程</view>
		<view class="sc_view">
			<scroll-view class="SV" scroll-y> 
				<block v-for="(item,index) in courseList" :key="index">
					<!-- 单格 -->
					<view class="partContainer">
						<view class="imgContainer">
							<image :src="item.classImgUrl" class="img" @tap="getCourseInfo(item.classId)"></image>
							<!-- <image src="../../static/image/icon/classIcon_D.png" class="img" @tap="getCourseInfo(item.classId)"></image> -->
						</view>
						<view class="course_title">
							<block v-if="item.className.length > 5">
								《{{item.className.substring(0,5)}}..》
							</block>
							<block v-else>
							   《{{item.className}}》
							</block>
						</view>
					</view>
				</block>
			</scroll-view>
		</view>
		
	</view>
</template>

<script>
	import { courseList } from '../../data/AllData.js'
	import * as api from "../../Api/api.js"
	export default{
		data(){
			return{
				courseList:null, 
			}
		},
		onLoad() {
			let practiveList = uni.getStorageSync('practiveList');
			if(practiveList == null || practiveList == ''){
				this.getAllCourseList()
			}else{
				this.courseList = practiveList;
			}
		},
		methods:{
			async getAllCourseList(){
				let that = this;
				api.getAllCourse({
					data:{},
					success:res=>{
						if(res.status == 200){
							that.courseList = res.data;
							uni.setStorageSync('practiveList',res.data)
						}
					},fail:err=>{
						uni.showToast({
							title:'网络延迟',
							icon:'none'
						})
					}
				})
			},
			getCourseInfo(e){
				uni.navigateTo({
					url:'choosePracticeType?id='+e
				})
			}
		}
	}
</script>

<style>
	page{
		height: 100%;
		width: 100%;
	}
	.Container{
		height: 100%;
		width: 100%;
		padding-top: 20rpx;
		padding: 20rpx 20rpx 40rpx 20rpx;
	}
	.title{
		font-size: 35rpx;
		font-weight: 600;
		margin-left: 10rpx;
	}
	.sc_view{
		margin-top: 20rpx;
		width: 100%;
		height: 93%;
	}
	.SV{
		height: 100%;
		width: 100%;
		margin: 0;
		padding: 10rpx;
	}
	.partContainer{
		width: 31%;
		float: left;
		height: 400rpx;
		margin-top: 10rpx;
		margin-left: 10rpx;
		margin-top: 10rpx;
		padding-top: 10rpx;
	}
	.imgContainer{
		width: 100%;
		height: 300rpx;
		text-align: center;
	}
	.img{
		width: 95%;
		height: 300rpx;
		border-radius: 0.2em;
	}
	.course_title{
		width: 100%;
		height: 50rpx;
		line-height: 50rpx;
		font-size: 28rpx;
		font-weight: 400;
		color: #3c3f2a;
		text-align: center;
		margin-top: 10rpx;
	}
</style>
