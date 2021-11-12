<template>
	<!-- 个人信息 -->
	<view class="container">
		<view class="body">
			<view class="infoContainer">
				<view class="title">
					姓名
				</view>
				<view class="contentContainer">
					<input class="content" maxlength="6" v-model="name"/>
				</view>
				<view class="rightHead">
					<view class="lg text-gray cuIcon-right , infoImg"></view>
				</view>
			</view>
			
			<view class="infoContainer">
				<view class="title">
					性别
				</view>
				<view class="contentContainer">
					<picker @change="bindPickerChange" :value="sexIndex" :range="sexArray">
						<view class="content">{{sexArray[sexIndex]}}</view>
					</picker>
				</view>
				<view class="rightHead">
					<view class="lg text-gray cuIcon-right , infoImg"></view>
				</view>
			</view>
			
			<view class="infoContainer">
				<view class="title">
					民族
				</view>
				<view class="contentContainer">
					<picker @change="bindNationPickerChange" :value="nationIndex" :range="nationArray">
						<view class="content" v-if="nationIndex == -1">{{nation}}</view>
						<view class="content" v-else>{{nationArray[nationIndex]}}</view>
					</picker>
				</view>
				<view class="rightHead">
					<view class="lg text-gray cuIcon-right , infoImg"></view>
				</view>
			</view>
			
			<view class="infoContainer">
				<view class="title">
					籍贯
				</view>
				<view class="contentContainer">
					<input class="content" maxlength="10" v-model="nationPlace"/>
				</view>
				<view class="rightHead">
					<view class="lg text-gray cuIcon-right , infoImg"></view>
				</view>
			</view>
			
			<view class="infoContainer">
				<view class="title">
					手机号
				</view>
				<view class="contentContainer">
					<input class="content" maxlength="11" v-model="phone"/>
				</view>
				<view class="rightHead">
					<view class="lg text-gray cuIcon-right , infoImg"></view>
				</view>
			</view>
			
			<view class="infoContainer">
				<view class="title">
					院系
				</view>
				
				<view class="contentContainer">
					<input class="content" maxlength="12" v-model="department"/>
				</view>
				<view class="rightHead">
					<view class="lg text-gray cuIcon-right , infoImg"></view>
				</view>
			</view>
			
			<view class="infoContainer">
				<view class="title">
					班级
				</view>
				<view class="contentContainer">
					<input class="content" maxlength="8" v-model="grade"/>
				</view>
				<view class="rightHead">
					<view class="lg text-gray cuIcon-right , infoImg"></view>
				</view>
			</view>
			
			<view class="infoContainer">
				<view class="title">
					所属支部
				</view>
				<view class="contentContainer">
					<input class="content" maxlength="8" v-model="branch"/>
				</view>
				<view class="rightHead">
					<view class="lg text-gray cuIcon-right , infoImg"></view>
				</view>
			</view>
			<view class="buttonContainer">
				<button class="button" type="warn" plain @tap="updateInfo">确认修改</button>
			</view>
		</view>
	</view>
</template>

<script>
	import { isLogin } from '../../method/MethodApi.js'
	import { nation } from '../../data/AllData.js'
	import * as api from '../../Api/api.js'
 	export default {
		data(){
			return{
				user:null,
				name:'',
				sexIndex: 0,
				sexArray: ['女', '男', '无'],
				nation:'',
				nationArray:nation,
				nationIndex:-1,
				nationPlace:"",
				phone:'',
				department:'',
				grade:'',
				branch:'',
			}
		},
		onLoad() {
			this.getSessionUser();
		},
		methods:{
			// 获取登陆用户
			getSessionUser(){
				let user = uni.getStorageSync('user');
				this.user = user;
				this.name = user.name;
				this.sexIndex = user.sex;
				this.nation = user.nation;
				this.nationPlace = user.nationPlace;
				this.phone = user.phone;
				this.department = user.department;
				this.grade = user.grade;
				this.branch = user.branch;
			},
			bindPickerChange: function(e) {
				// console.log('picker发送选择改变，携带值为', e.target.value)
				this.sexIndex = e.target.value
			},
			bindNationPickerChange:function(e){
				// console.log('picker发送选择改变，携带值为', e.target.value)
				this.nationIndex = e.target.value
			},
			// 修改用户信息
			updateInfo(){
				let name = this.name;
				let sex = this.sexIndex;
				let nation = null;
				if(this.nationIndex != -1){
					nation = this.nationArray[this.nationIndex];
				}else{
					nation = this.nation;
				}
				let nationPlace = this.nationPlace;
				let phone = this.phone;
				let department = this.department;
				let grade = this.grade;
				let branch = this.branch;
				
				let user = uni.getStorageSync('user');
				let that = this;
				user.name = name;
				user.sex = sex;
				user.nation = nation;
				user.nationPlace = nationPlace;
				user.phone = phone;
				user.department = department;
				user.grade = grade;
				user.branch = branch;
				// console.log(user)
				api.updateUserInfo({
					data:{
						userId:user.userId,
						name:user.name,
						sex:user.sex,
						nation:user.nation,
						nationPlace:user.nationPlace,
						phone:user.phone,
						department:user.department,
						grade:user.grade,
						branch:user.branch,
					},
					success:res=>{
						// console.log(res);
						if(res.status == 200){
							uni.showToast({
								title:"修改完成",
								icon:'none',
								success:function(){
									uni.setStorageSync('user',user);
								}
							})
						}else{
							uni.showToast({
								title:"修改失败",
								icon:'none',
							})
						}
					},
					fail:err=>{
						uni.showToast({
							title:"网络延迟",
							icon:"none"
						})
					}
				})
			},
			changeImg(){
				console.log("修改头像")
				const _this = this;
				uni.showActionSheet({
					itemList: ['相册', '拍照'],
					success: (res) => {
						// console.log('选中了第' + (res.tapIndex + 1) + '个按钮');
						if (res.tapIndex == 0) {
							// 用户点击了从图库上传
							// console.log("相册")
							uni.chooseImage({
								count: 1,
								sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
								sourceType: ['album'], //从相册选择
								success: (res) => {
									// 选择图片后, 返回的数据
									// console.log("相册")
									// console.log(res.tempFilePaths[0])
									this.pushPhotoToService(res.tempFilePaths[0])
								}
							})
						} else {
							// console.log("照相")
							uni.chooseImage({
								count: 1,
								sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
								sourceType: ['camera'], //从摄像头选择
								success: (res) => {
									// console.log(res.tempFilePaths[0])
									this.pushPhotoToService(res.tempFilePaths[0])
								}
							})
						};
					},
					fail: (res) => {
						console.log(res.errMsg);
					}
				});
			},
			// async pushPhotoToService(value) { 
			// 	console.log(value)
			// 	const uploadTask = uni.uploadFile({
			// 		url: 'http://42.192.137.173:10010/api/upload/image',
			// 		filePath: value,
			// 		name: 'file',
			// 		success: (uploadFileRes) => {
			// 			this.updatePhoto(uploadFileRes.data)
			// 		}
			// 	});
			// },

			// async updatePhoto(photo) { 
			// 	try {
			// 		const value = uni.getStorageSync('WD_TOKEN').WD_TOKEN;
			// 		if (value.WD_TOKEN !== null) {
			// 			const updatePicture = await UpdatePicture({
			// 				uPhoto: photo,
			// 				token: value
			// 			})
			// 			if (updatePicture !== null) {
			// 				uni.showToast({
			// 					title: "客官,头像修改成功了呢",
			// 					icon: "none"
			// 				})
			// 				uni.switchTab({
			// 					url: '../../navigator/mine',
			// 					success(res) {
			// 						console.log(res)
			// 					},
			// 					fail(err) {
			// 						console.log(err)
			// 					}
			// 				});
			// 			}
			// 		}
			// 	} catch (e) {
			// 		console.log(e)
			// 	}
			// },
		}
	}
</script>

<style>
	page{
		width: 100%;
		height: 100%;
		background-color: white;
	}
	.body{
		margin-top: 10upx;
		width: 100%;
		height: 400upx;
	}
	.infoContainer{
		/* width: 100%; */
		width: 90%;
		margin-left: 5%;
		height: 100upx;
		/* padding: 20upx 40upx 30upx 40upx; */
		padding: 20upx 0upx 0upx 0upx;
		background-color: white;
		display: flex;
		border-bottom: 1px solid rgba(239,239,239,1);
	}
	.infoContainer:last-child{
		border: 0;
	}
	.title{
		float: left;
		font-size: 35upx;
		font-weight: 600;
	}
	.contentContainer{
		position: right;
		text-align: right;
		position: absolute;
		height: 60upx;
		right: 100upx;
		line-height: 60upx;
	}
	.content{
		font-size: 40upx;
		font-weight:500;
		color: rgba(204,204,204,1);
	}
	.rightHead{
		text-align: right;
		position: absolute;
		width: 60upx;
		height: 60upx;
		right: 30upx;
		line-height: 60upx;
	}
	.infoImg{
		font-size: 40upx;
	}
	.buttonContainer{
		width: 100%;
		height: 80rpx;
		margin-top: 60upx;
		text-align: center;
	}
	.button{
		width: 60%;
		/* background-color: #ff98a2; */
		border-radius: 20upx;
		font-size: 40upx;
		color: #444444;
	}
</style>
