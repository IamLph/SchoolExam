<template>
	<view class="container">
		<block>
			<!-- 搜索框 -->
			<view class="searchContainer">
				<image src="../../static/image/icon/searchIcon.png" class="searchIcon" v-show="searchIcon"></image>
				<input type="text" 
					:class="centerOrLeft == true ? 'serachInputCenter' : 'serachInputLeft'" 
					placeholder="搜索" 
					v-model="searchText"
					@focus="hideIcon"
					@blur="showIcon"
					maxlength="12">
				<image src="../../static/image/icon/clearSearch.png" 
					class="clearIcon" 
					@click="clearSearchText()" 
					v-show="clearIcon"></image>
			</view>
			
			<view class="body" v-show="!searchIng">
				<scroll-view class="scrollView" scroll-y>
					<block v-for="(item,index) in courseList" :key="index">
						<scroll-view scroll-x class="scrollSwiper" :scroll-left="scrollLeft">
							<view :class="item.site == 1 ? 'classContainerIsTop':'classContainer'" @click="getClassVideo(item.classId,item.className)">
								<view>
									<view class="classIconContainer">
										<image :src="item.classImgUrl" class="classIcon"></image>
									</view>
									<view class="classInfoContainer">
										<view class="textContainer">
											<view class="className">
												{{item.className}}
											</view>
											<view class="teacherName">{{item.classTeacherName}}</view>
										</view>
										<view class="rightHead">
											<view class="lg text-gray cuIcon-right , rightHead_img"></view>
										</view>
									</view>	
								</view>
							</view>
							<!-- 功能选择 -->
							<view class="moveContainer">
								<view class="moveSet" v-if="item.site == 0" @tap="topSet(false,item.classId)">
									<view class="moveTextContainer">
										<text class="moveText moveTextTop">置顶</text>
									</view>
								</view>
								<view class="moveSet" v-if="item.site == 1" @tap="topSet(true,item.classId)">
									<view class="moveTextContainer moveTextContainerNoTop">
										<text class="moveText moveTextNoTop" decode=true>取消\n置顶</text>
									</view>
								</view>
								<!-- 手动占位W^_^W -->
								<view style="width: 23upx;display: inline-block;"></view>
							</view>
						</scroll-view>
					</block>
				</scroll-view>
			</view>
			<view class="body" v-show="searchIng">
				<scroll-view class="scrollView" scroll-y>
					<block v-for="(item,index) in searchClassList" :key="index">
						<scroll-view scroll-x class="scrollSwiper" :scroll-left="scrollLeft">
							<view :class="item.site == 1 ? 'classContainerIsTop':'classContainer'" @click="getClassVideo(item.classId,item.className)">
								<view>
									<view class="classIconContainer">
										<image :src="item.classImgUrl" class="classIcon"></image>
									</view>
									<view class="classInfoContainer">
										<view class="textContainer">
											<view class="className">
												{{item.className}}
											</view>
											<view class="teacherName">{{item.teacherName}}</view>
										</view>
										<view class="rightHead">
											<view class="lg text-gray cuIcon-right , rightHead_img"></view>
										</view>
									</view>	
								</view>
							</view>
							<!-- 功能选择 -->
							<view class="moveContainer">
								<view class="moveSet" v-if="item.site == 0" @tap="topSet(false,item.classId)">
									<view class="moveTextContainer">
										<text class="moveText moveTextTop">置顶</text>
									</view>
								</view>
								<view class="moveSet" v-if="item.site == 1" @tap="topSet(true,item.classId)">
									<view class="moveTextContainer moveTextContainerNoTop">
										<text class="moveText moveTextNoTop" decode=true>取消\n置顶</text>
									</view>
								</view>
								<!-- 手动占位W^_^W -->
								<view style="width: 23upx;display: inline-block;"></view>
							</view>
						</scroll-view>
					</block>
				</scroll-view>
			</view>
		</block>
		<!-- <block v-else>
			<view class="noLogin">
				<text class="noLoginText">更多功能请先登陆</text>
			</view>
		</block> -->
	</view>
</template>
 
<script>
	import {videoInfoList} from '../../data/AllData.js'
	import { isLogin } from '../../method/MethodApi.js'
	import * as api from '../../Api/api.js'
	export default {
		data(){
			return{
				searchText:"",
				searchIcon:true,
				clearIcon:false,
				centerOrLeft:true,
				// 课程集合
				courseList:"",
				viewId:'',
				scrollLeft:0,
				searchIng:false,
				searchClassList:"",
				// isLogin:true,
			}
		},
		watch:{
			searchText:{
				handler(newVal,oldVal) {
					let list = this.courseList
					let newList = []
				    if(newVal != ""){
						this.clearIcon = true
						this.searchIng = true
						// 进行模糊查询搜索
						for(var i = 0 ; i < list.length ; i++){
							if(list[i].className.indexOf(newVal) >= 0){
								newList.push(list[i])
							}
						}
						this.searchClassList = newList
						// console.log(newList)
					}else{
						this.clearIcon = false
						this.searchIng = false
					}
				},
			}
		},
		onLoad(){
			// 判断是否登陆
			let list = uni.getStorageSync("courseList");
			if(list == null || list == ''){
				this.getAllCourse();
			}else{
				this.setTop(list)
			}
		},
		methods:{
			/**
			 * 获取所有课程
			 */
			getAllCourse(){
				let that = this;
				api.getAllCourse({
					data:{},
					success:res=>{
						console.log("获取所有课程")
						console.log(res)
						if(res.status == 200){
							let list = res.data;
							that.setTop(list)
							that.setTop(uni.getStorageSync("courseList"));
						}
					},fail:err=>{
						uni.showToast({
							title:"网络延迟",
							icon:"none"
						})
					}
				})
			},
			// 设置缓存
			setSessionCourseList(list){
				uni.setStorageSync("courseList",list);
			},
			/**
			 * Top排序
			 * @param {Object} list 传递一个数组进行排序
			 */
			setTop(list){
				// 获取最新的课程集合
				// 排序
				let newList = [];
				for(var i = 0 ; i < list.length ; i ++ ){
					if(list[i].site == 1){
						newList.unshift(list[i]);
					}else{
						newList.push(list[i])
					}
				}
				this.courseList = newList
				this.setSessionCourseList(newList);
			},
			clearSearchText(){
				// console.log("清空输入")
				this.searchText = "";
			},
			// 聚焦隐藏标签
			hideIcon(){
				// console.log("隐藏标签")
				this.searchIcon = false
				this.centerOrLeft = false
			},
			// 离开获取标签
			showIcon(){
				this.searchIcon = true	
				this.centerOrLeft = true
			},
			// 修改课程位置
			changeClassList(id,site){
				let list = this.courseList
				for (var i = 0 ; i < list.length ; i++){
					if(list[i].classId == id){
						console.log(list[i])
						// console.log("修改了====>前为:"+list[i].site)
						list[i].site = site
						// console.log("修改后为:"+list[i].site)
						break;
					}
				}
				// 重新置顶
				this.setTop(list);
			},
			/**
			 * 改变置顶
			 * @param {Object} b 是否置顶的意思，如果是false，取消置顶，如果是true，就设置为置顶
			 * @param {Object} id 当前选中的课程的id
			 */
			topSet(b,id){
				// 修改上面的数组
				if(!b){
					// console.log("进行置顶了")
					this.changeClassList(id,1)
				}else{
					// console.log("取消置顶了")
					this.changeClassList(id,0)
				}
				this.scrollLeft = Math.random();  
				
			},
			getClassVideo(classId,name){
				uni.navigateTo({
					url:'../Study/videoStudyInfo?id='+classId+"&name="+name,
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
		background-color: rgba(245, 246, 248, 1.0);
	}
	/* padding上右下左 */
	.searchContainer{
		width: 100%;
		height: 110upx;
		padding: 20upx 40upx 15upx 40upx;
		position: relative;
	}
	.searchIcon{
		width: 45upx;
		height: 45upx;
		position: absolute;
		margin-left: 34%;
		margin-top: 13upx;
	}
	.serachInputCenter{
		height: 70upx;
		width: 94%;
		margin-left: 3%;
		text-align: center;
		background-color: white;
		border-radius: 1em;
		font-size: 33upx;
		line-height:70upx;
		vertical-align:middle;
	}
	.serachInputLeft{
		height: 70upx;
		width: 94%;
		margin-left: 3%;
		text-align: left;
		background-color: white;
		border-radius: 1em;
		font-size: 33upx;
		line-height:70upx;
		vertical-align:middle;
		text-indent:30upx
	}
	.clearIcon{
		width: 44upx;
		height: 44upx;
		position: absolute;
		margin-left: 80%;
		margin-top: -58upx;
	}
	.body{
		width: 100%;
		height: 98%;
	}
	/* 纵向滚动 */
	.scrollView{
		width: 100%;
		height: 93%;
		padding-top: 10upx;
	}
	/* 横向滚动 */
	.scrollSwiper{
		width: 100%;
		height: 140upx;
		margin-bottom: 30upx;
		white-space: nowrap;
	}
	.classContainer{
		width: 94%;
		margin-left: 3%;
		height: 139upx;
		padding: 20upx 10upx 18upx 24upx;
		border-radius: 0.4em;
		box-shadow: 1px 1px 1px 1px rgba(221, 216, 220, 1.0);
		position: relative;
		float: left;
		display: inline-block;
		background-color: white;
	}
	.classContainerIsTop{
		width: 94%;
		margin-left: 3%;
		height: 139upx;
		padding: 20upx 10upx 18upx 24upx;
		border-radius: 0.4em;
		box-shadow: 1px 1px 1px 1px rgba(221, 216, 220, 1.0);
		position: relative;
		float: left;
		display: inline-block;
		background-color: rgba(245,246,250,1);
	}
	.classIconContainer{
		width: 104upx;
		height: 104upx;
		border-radius: 0.5em;
	}
	.classIcon{
		width: 104upx;
		height: 104upx;
		border-radius: 0.5em;
	}
	.classInfoContainer{
		margin-left: 120upx;
		display: flex;
		margin-top: -104upx;
		height: 104upx;
	}
	.textContainer{
		float: left;
	}
	.className{
		margin-top: 10upx;
		font-size: 30upx;
		font-weight: 600;
	}
	.teacherName{
		margin-top: 5upx;
		font-size: 26upx;
		font-weight: 400;
	}
	.rightHead{
		position: absolute;
		right: 10upx;
		width: 60upx;
		height: 60upx;
		text-align: center;
		margin-top: 21upx;
	}
	.rightHead_img{
		font-size: 50upx;
		/* margin-top: ; */
	}
	/* 功能容器 */
	.moveContainer{
		height: 140upx;
		width: 80%;
		padding: 20upx;
	}
	/* 圆球 */
	.moveSet{
		width: 100upx;
		height: 100upx;
		border-radius: 50%;
		display: inline-block;
		text-align: center;
		margin-left: 30upx;
		background-color: rgba(201,206,209,1);
	}
	/* 文字容器 */
	.moveTextContainer{
		width: 60upx;
		height: 60upx;
		margin: auto;
		margin-top: 26upx;
	}
	.moveText{
		margin-top: 10upx;
		font-size: 30upx;
		color: rgba(253,254,254,1);
	}
	.moveTextContainerNoTop{
		margin-top: 18upx;
	}
	.moveTextNoTop{
		font-size: 26upx;
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