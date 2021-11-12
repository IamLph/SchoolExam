<template>
	<view class="container">
		<!-- <view class="top">
			学习新思想，争做新青年！
		</view> -->
		<view class="classVideoContainer">
			<scroll-view scroll-y class="scrollView">
				<block v-for="(item,index) in videoInfoList" :key="index">
					<view class="unitPart" @click="watchVideo(item.videoUrl,item.id,item.videoName,item.initTime,item.videoIsOk)">
						<view class="partContainer">
							<view class="classImgContainer">
								<image class="classImg" :src="item.videoImg">
								</image>
							</view>
							<view class="contentContainer">
								<view class="contentTitle">{{item.videoName}}</view>
								<view class="contentInfo">
									<view v-if="item.videoInfo.length > 24">
										{{item.videoInfo.substring(0,24)}}...
									</view>
									<view v-else>
										{{item.videoInfo}}
									</view>
								</view>
								
								<view class="contentTime">
									<view>
										发布时间:{{item.publishTime}}
									</view>
									<view v-if="item.remainTime != null">剩余时间:{{item.remainTime}}分</view>
									<view v-else>剩余时间:--分</view>
								</view>
							</view>
						</view>
					</view>
				</block>
			</scroll-view>
		</view>
	</view>
</template>

<script>
	// import {videoInfoList} from '../../data/AllData.js'
	import {isLogin} from '../../method/MethodApi.js'
	import * as api from '../../Api/api.js'
	export default{
		data(){
			return{
				videoInfoList:null,
				isLogin:false,
			}
		},
		onLoad(option){
			this.isLogin = isLogin();
			// 课程ID
			let classId = option.id;
			
			let className = option.name
			// 修改标题
			uni.setNavigationBarTitle({
			    title: className
			});
			if(this.isLogin){
				let userId = uni.getStorageSync('user').userId;
				// console.log("用户登陆获得的Id:"+userId)
				this.getVideoByClassAndUserId(classId,userId);
			}else{
				this.getVideoByClassId(classId);
			}
		},
		methods:{
			// 用户未登陆的化，直接根据课程id获取视频
			getVideoByClassId(classId){
				let that = this;
				api.getVideoByClassId({
					data:{
						classId:classId
					},success:res=>{
						if(res.status == 200){
							that.videoInfoList = res.data
						}
					},fail:err=>{
						uni.showToast({
							title:"网络延迟",
							icon:"none"
						})
					}
				})
			},
			// 用户登陆的化根据课程id与用户id获取视频
			getVideoByClassAndUserId(classId,userId){
				// console.log(id)
				let that = this;
				api.getVideoInfoByUserId({
					data:{
						classId:classId,
						userId:userId
					},success:res=>{
						// console.log(res)
						if(res.status == 200){
							if(res.data.length == 0){
								that.getVideoByClassId(classId);
							}else{
								that.videoInfoList = res.data
							}
							
						}
					},fail:err=>{
						uni.showToast({
							title:"网络延迟",
							icon:"none"
						})
					}
				})
			},
			/**
			 * @param {Object} url 视频地址
			 * @param {Object} id  视频id
			 * @param {Object} videoName 视频名字
			 * @param {Object} initTime 视频初始化时间
			 * @param {Object} videoIsOK 视频是否播放完毕
			 */
			watchVideo(url,id,videoName,initTime,videoIsOK){
				uni.navigateTo({
					url:'video?id='+id,
					success: function(res) {
					    // 通过eventChannel向被打开页面传送数据
					    res.eventChannel.emit('watchVideo', { 
							url:url,
							name:videoName,
							initTime:initTime,
							videoIsOK:videoIsOK
						})
					}
				})
			}
		}
	}
</script>

<style>
	page{
		width: 100%;
		height: 100%;
	}
	.container{
		width: 100%;
		height: 100%;
	}
	.top{
		width: 100%;
		height: 80upx;
		/* background-color: rgba(255, 255, 0, 1.0); */
		text-align: center;
		font-size: 36upx;
		line-height: 80upx;
	}
	.classVideoContainer{
		width: 100%;
		/* height: 92%; */
		height: 95%;
		padding: 10px 20upx 20upx 20upx;
		display: inline-block;
	}
	.scrollView{
		height: 100%;
		width: 100%;
		padding: 10upx 14upx 10upx 20upx;
	}
	.unitPart{
		width: 100%;
		height: 230upx;
		padding-bottom: 30upx;
		margin-bottom: 30upx;
		border-bottom: 1px solid rgba(229, 227, 228, 1.0);
		/* background-color: pink; */
	}
	.classImgContainer{
		width: 300upx;
		height: 200upx;
		border-radius: 0.4em;
		/* background-color: #0066CC; */
	}
	.classImg{
		width: 300upx;
		height: 200upx;
		border-radius: 0.4em;
	}
	.partContainer{
		display: flex;
	}
	.contentContainer{
		display: inline-block;
		margin-left: 18upx;
		position: relative;
		width: 100%;
	}
	.contentTitle{
		margin-top: 10upx;
		font-size: 30upx;
		font-weight: 600;
	}
	.contentInfo{
		/* background-color: green; */
		font-size: 27upx;
		font-weight: 400;
	}
	.contentTime{
		position: absolute;
		bottom: 4upx;
		font-size: 26upx;
		font-weight: 400;
		color: rgba(153,153,153,1);
	}
		
</style>
