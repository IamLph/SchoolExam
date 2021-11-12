<template>
	<view class="content">
		<!-- 首页轮播图 -->
		<!-- <swiper class="screen-swiper" :class="dotStyle?'square-dot':'round-dot'" :indicator-dots="true" :circular="true"
		 :autoplay="true" :interval="SWIPERTIME" duration="500">
			<swiper-item v-for="(item,index) in INDEXIMGLIST" :key="item.id" >
				<image :src="item.src" mode="aspectFill"></image>
			</swiper-item>
		</swiper> -->
		<!-- 轮播图2 -->
		<swiper class="card-swiper" :class="dotStyle?'square-dot':'round-dot'" :indicator-dots="true" :circular="true"
		 :autoplay="true" :interval="SWIPERTIME" duration="500" @change="cardSwiper" indicator-color="#8799a3"
		 indicator-active-color="#0081ff">
			<swiper-item v-for="(item,index) in INDEXIMGLIST" :key="item.id" :class="cardCur==index?'cur':''">
				<view class="swiper-item">
					<image :src="item.src" mode="aspectFill"></image>
				</view>
			</swiper-item>
		</swiper>
		<block v-if="isLogin">
			<!-- 四个功能 -->
			<view class="gridOp">
				<view class="gridOp_Icon" v-for="(item,index) in INDEXLIST" :key="index" @click="option(item.id)">
					<view>
						<image :src="item.src" class="gridOp_Img"></image>
					</view>
					<text class="gridOp_Text">{{item.text}}</text>
				</view>
			</view>
			<!-- 考试通知 -->
			<view class="news_text" @tap="getMorNews">
				<view class="news_text_view">考试通知</view>
				<view class="lg text-gray cuIcon-right , news_text_img"></view>
			</view>
			<!-- 考试通知列表 -->
			<Notice :examNotice='examNotice' :all="false"></Notice>
		</block> 
		<block v-else>
			<view class="noLogin">
				<text class="noLoginText">更多功能请先登陆</text>
			</view>
		</block>
		<br>
	</view>
</template>

<script>
	import {
		examNotice
	} from '../../data/AllData.js'
	import {
		INDEXLIST,
		INDEXIMGLIST,
		SWIPERTIME
	} from '../../data/DataSetting.js'
	import Notice from '../component/Notice.vue'
	import { isLogin } from '../../method/MethodApi.js'
	import * as api from '../../Api/api.js'
	export default {
		components:{
		    Notice
		},
		data() {
			return {
				// 四个功能
				INDEXLIST: INDEXLIST,
				// 首页轮播图
				INDEXIMGLIST: INDEXIMGLIST,
				// 轮播时间
				SWIPERTIME:SWIPERTIME,
				dotStyle: false,
				// 考试通知列表
				examNotice: null,
				cardCur: 0,
				isLogin:false
			}
		},
		onLoad() {
			// 判断是否登陆
			this.isLogin = isLogin();
			if(this.isLogin){
				let user = uni.getStorageSync('user');
				let userId = user.userId;
				this.setExam(userId);
			}
		},
		methods: {
			// 根据用户获取要考试的信息
			setExam(userId){
				let that = this;
				api.getExamInfoById({
					data:{},success:res=>{
						// console.log(res)
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
			// 第一个轮播图需要，colorUi,不可删
			DotStyle(e) {
				this.dotStyle = e.detail.value
			},
			cardSwiper(e) {
				this.cardCur = e.detail.current
			},
			getMorNews() {
				uni.navigateTo({
					url:"../Index/AllNotice"
				})
			},
			// 选择功能
			option(id) {
				switch (id) {
					case 1:
						{
							uni.navigateTo({
								url:"../practice/userWrongPractice"
							})
						}
						break;
					case 2:
						{
							uni.navigateTo({
								url: '../practice/choosePractice'
							})
						}
						break;
					case 3:
					// 收藏题
						{
							uni.navigateTo({
								url:"../practice/userFavorPractice"
							})
						}
						break;
					case 4:
						{
							uni.showToast({
								title: "更多功能正在开发!",
								icon: "none"
							})
						}
					default:
						break;
				}
			},
		}
	}
</script>

<style>
	page {
		overflow-x: hidden;
		width: 100%;
	}

	.content {
		background-color: rgb(255, 255, 255);
		height: 100%;
	}

	.gridOp {
		width: 100%;
		height: 150upx;
		text-align: center;
		margin-top: 36upx;
	}

	.gridOp_Icon {
		display: inline-block;
		width: 25%;
		text-align: center;
	}

	.gridOp_includeImg {
		width: 100rpx;
		height: 100rpx;
		margin: auto;
		text-align: center;
		background-color: rgba(229, 46, 40, 1);
		border-radius: 50%;
	}

	.gridOp_Img {
		width: 100rpx;
		height: 100rpx;
	}

	.gridOp_Text {
		font-size: 27rpx;
		color: rgba(126, 136, 136, 1);
		font-weight: 500;
	}

	/* 考试通知 */
	.news_text {
		width: 250rpx;
		height: 50upx;
		margin-top: 20upx;
		display: flex;
		margin-left: 10rpx;
	}
	.news_text_view {
		float: left;
		margin-left: 10rpx;
		font-size: 40upx;
		font-weight: 1000;
		height: 50upx;
	}

	.news_text_img {
		height: 50upx;
		width: 50upx;
		color: black;
		font-size: 35upx;
		font-weight: 600;
		margin-top: 12upx;
		margin-left: 20upx;
	}

	.newsScoView {
		width: 94%;
		margin-left: 3%;
		margin-top: 40upx;
	}

	.oneCard {
		width: 100%;
		background-color: #007AFF;
		height: 220rpx;
		background: white;
		box-shadow: -1px 1px 2px 1px rgba(205, 205, 205, 1);
		padding: 5px 10px 5px 12px;
		margin-top: 20rpx;
		position: relative;
		margin-top: 40upx;
	}

	.FirstLine {
		display: flex;
		width: 100%;
		height: 50rpx;
	}

	.First_Left {
		line-height: 50rpx;
		width: 60%;
		font-size: 30rpx;
		color: #000000;
		font: bold;
	}

	.First_Right {
		line-height: 50rpx;
		width: 30%;
		margin-left: 10%;
		text-align: right;
	}

	.First_Right_Time {
		width: 150rpx;
		height: 50rpx;
		border-radius: 0.2em;
		float: right;
		text-align: center;
		color: #FFFFFF;
		font-weight: 400;
		background-color: #ef1233;
	}

	.SecondLine:first {
		margin-top: 15rpx;
	}

	.SecondLine {
		width: 100%;
		height: 50rpx;
		line-height: 50rpx;
	}

	.Second_Left {
		font-size: 28rpx;
		color: #636350;
	}

	.second_text {
		margin-left: 30rpx;
	}
	.noLogin{
		height: 20rpx;
		font-size: 30rpx;
		text-align: center;
		margin-top: 100rpx;
		font-weight: 600;
		color: #007AFF;
	}
</style>
