<template>
	<view v-show="hasQuestion">
		<view :class="block===0? 'block' : 'none'">
			<view class="login">
				练习结束
			</view>
		</view>
		<view :class="block===1? 'block' : 'none'">
			<!-- 头部 -->
			<view id="top-box" class="cu-bar bg-white solid-bottom">
				<view class="action text-black">
					<text>{{title}}</text>
				</view>
				
				<!-- 倒计时 -->
				<view class="action">
					<!-- <view class="cu-btn bg-green shadow" style="margin-right: 30upx;" data-target="modalCard">{{content}}</view> -->
					<view class="cu-btn bg-green shadow" style="margin-right: 30upx;" data-target="modalCard" v-show="isFinish">{{score}}分</view>
					<!-- 提交按钮 -->
					<view class="topSubmit" @tap="submit" v-show="!isFinish">
						提交
					</view>
				</view>
				
			</view>

			<!-- 提交 -->
			<view class="cu-modal" :class="modalCard=='modalError'?'show':''" @tap="hideCardModal">
				<view class="cu-dialog" @tap.stop>
					<scroll-view class="page padding" :scroll-y=true :style="{'height':swiperHeight}">
						<view class="cu-bar solid-bottom">
							<view class="action eee">
								<text class="cuIcon-title text-red"></text>答题卡
								<button class="cu-btn bg-green shadow submit" 
									data-target="modalCard" 
									@tap="submit" v-show="!isFinish">
									提交
								</button>
							</view>
						</view>
						<view class="grid col-5 ">
							<view class="margin-tb-sm text-center" v-for="(subject,index) in subjectList" :key="index">
								<button class="cu-btn round" :class="[subject.userAnswer.length===0?'line-grey':'bg-red']" @click="AppointedSubject(index)">{{index+1}}</button>
							</view>
						</view>
					</scroll-view>
				</view>
			</view>

			<form>
				<swiper :current="subjectIndex" class="swiper-box" @change="SwiperChange" :style="{'height':swiperHeight}">
					<swiper-item v-for="(subject,index) in subjectList" :key="index">

						<view v-if="index-subjectIndex>=-1&&index-subjectIndex<=1">

							<view class="cu-bar bg-white solid-bottom">
								<view class="action text-black">
									<!-- 题目 -->
									<!-- <text class="cuIcon-title text-red"></text><text v-html="subject.title"/> -->
									{{index+1}}.<text v-html="subject.title"/>
								</view>
							</view>

							<view>
								<!-- 判断题 -->
								<radio-group class="block" @change="RadioboxChange" v-if="subject.type==3">
									<view class="cu-form-group" v-for="(option,index) in subject.optionList" :key="index">
										<radio :value="option.prefix" :checked="subject.userAnswer.indexOf(option.prefix) > -1?true:false" :disabled="isFinish"></radio>
										<view class="title text-black">{{option.prefix}}.<text v-html="option.content"/></view>
									</view>
								</radio-group>
								<!-- 单选题 -->
								<radio-group class="block" @change="RadioboxChange" v-if="subject.type==1">
									<view class="cu-form-group" v-for="(option,index) in subject.optionList" :key="index">
										<radio :value="option.prefix" :checked="subject.userAnswer.indexOf(option.prefix) > -1?true:false" :disabled="isFinish"></radio>
										<view class="title text-black">{{option.prefix}}.<text v-html="option.content"/></view>
									</view>
								</radio-group>
								<!-- 多选题 -->
								<checkbox-group class="block" @change="CheckboxChange" v-else-if="subject.type==2">
									<view class="cu-form-group" v-for="(option,index) in subject.optionList" :key="index">
										<checkbox :value="option.prefix" :class="subject.userAnswer.indexOf(option.prefix) > -1?'checked':''" :checked="subject.userAnswer.indexOf(option.prefix) > -1?true:false" :disabled="isFinish"></checkbox>
										<view class="title  text-black">{{option.prefix}}.<text v-html="option.content"/></view>
									</view>
								</checkbox-group>
							</view>
							<!-- 答案解析 -->
							<view v-show="showAnswer || showAllAnswer" class="margin-top solid-top">
								<!-- 渲染正确答案 -->
								<view class="cu-bar">
									<view class="action text-grey">
										<text>正确答案：</text>
										<text class="solid-bottom  padding-left text-green"><text v-html="subject.answer"></text></text>
									</view>
								</view>
								<!-- 渲染解析内容 -->
								<view class="cu-bar cu-bar-title">
									<view class="action  text-grey">
										<text>解析：</text>
									</view>
								</view>
								<!-- 解析内容 -->
								<view class="text-content padding  text-grey">
									<text v-html="subject.explain"></text>
								</view>
							</view>
						</view>
					</swiper-item>
				</swiper>
			</form>

			<!-- 底部 -->
			<view id="foot-box" class="cu-bar tabbar bg-white shadow foot">
				<view class="action" @click="MoveSubject(-1)">
					<view class="cuIcon-cu-image">
						<text class="lg cuIcon-back text-gray"></text>
					</view>
					<view class="text-gray">上一题</view>
				</view>

				<view class="action" @click="MoveSubject(1)">
					<view class="cuIcon-cu-image">
						<text class="lg text-gray cuIcon-right"></text>
					</view>
					<view class="text-gray">下一题</view>
				</view>

				<view class="action" @click="FavorSubject()" :class="[userFavor?'text-red':'text-gray']">
					<view class="cuIcon-cu-image">
						<text class="lg cuIcon-favor"></text>
					</view>
					<view>收藏</view>
				</view>

				<view class="action" @click="ShowAnswerChange">
					<view class="cuIcon-cu-image">
						<text class="lg text-gray cuIcon-question"></text>
					</view>
					<view class="text-gray">解答</view>
				</view>

				<view class="action" @tap="showCardModal" data-target="modalError">
					<view class="cuIcon-cu-image">
						<text class="lg text-gray cuIcon-form"></text>
					</view>
					<view class="action">
						<view class="shadow">答题卡</view>
					</view>
				</view>
			</view>
		</view>
	</view>

</template>

<script>
	import practice from "../../data/practice.json"
	// import {examList} from '../../data/ExamTest.js'
	import {TIME,QUESTIONNUM} from '../../data/DataSetting.js'
	import {getScore} from '../../method/MethodApi.js'
	import * as api from '../../Api/api.js'
	export default {
		data() {
			return {
				userFavor: false, //是否已收藏
				currentType: 0, //当前题型
				subjectIndex: 0, //跳转索引
				autoShowAnswer: false, //答错是否显答案
				autoRadioNext: true, //判断题、单项题，自动移下一题
				// 滑块高度
				swiperHeight: '800px', 
				title: '题目收藏',
				// 考试信息数据,考试试卷
				subjectList: null,
				//显示答题卡
				modalCard: null, 
				seconds: TIME.seconds, //秒
				minutes: TIME.minutes, //分
				content: "", //内容
				clock: undefined, //时钟
				block: 0,
				//封装问题结果
				questions: [],
				live:0,
				// 练习类型
				type:'',
				// 是否显示答案
				showAnswer:false,
				// 提交显示全部答案
				showAllAnswer:false,
				isFinish:false,
				score:'',
				// 试题数目
				QUESTIONNUM :QUESTIONNUM ,
				hasQuestion:false,
			}
		},
		onHide() {
			// 监听离开次数
			// this.live ++ ;
			// console.log(this.live);
		},
		onReady() {
			var tempHeight = 800;
			var _me = this;
			uni.getSystemInfo({
				//获取手机屏幕高度信息，让swiper的高度和手机屏幕一样高                
				success: function(res) {
					// //这里是手机屏幕高度                    
					tempHeight = res.windowHeight;
					uni.createSelectorQuery().select("#top-box").fields({
						size: true,
						scrollOffset: true
					}, (data) => {
						tempHeight -= data.height;
						uni.createSelectorQuery().select("#foot-box").fields({
							size: true,
							scrollOffset: true
						}, (data) => {
							tempHeight -= data.height;
							_me.swiperHeight = tempHeight + 'px';
						}).exec();
					}).exec();
				}
			});
		},
		onLoad(option) {
			this.block = 1;
			this.content = this.minutes+":"+this.seconds;
			let userId = uni.getStorageSync('user').userId;
			this.getFavorQuestionByUserId(userId);
		},
		methods: {
			getFavorQuestionByUserId(userId){
				let that = this;
				api.getUserFavor({
					data:{
						userId:userId
					},success:res=>{
						// console.log(res)
						if(res.status == 200){
							if(res.data.length != 0){
								let data = res.data;
								let object = [];
								let userAnswer = ""
								that.userFavor = data[0].userFavor;
								for(var i=0;i<data.length;i++){
									// 获取问题，转化数组
									let json = JSON.parse(res.data[i].optionList);
									let jsonOptionList = [];
									for(var j=0;j<json.length;j++){
										jsonOptionList[j] = {
											"prefix": json[j].prefix,
											"content": json[j].content
										}
									}
									// console.log(jsonOptionList)
									if(data[i].userAnswer == null){
										userAnswer = ""
									}else{
										userAnswer = data[i].answer
									}
									object[i] = {
										"id":data[i].examId,
										"title": data[i].title,
										"type": data[i].type,
										"optionList": jsonOptionList,
										"answer": data[i].answer,
										"userAnswer": userAnswer,
										"userFavor": data[i].userFavor,
										"explain": data[i].explain
									}
								}
								that.subjectList = object;
								that.hasQuestion = true;
							}
						}else{
							uni.showToast({
								title:"还没有收藏题!",
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
			},
			setClock(){
				// 设置定时器
				this.clock = setInterval(() => { //倒计时
					this.block = 1
					this.seconds--
					this.content = this.minutes + ":" + (this.seconds = ((this.seconds >= 1 && this.seconds < 10) ? "0" + this.seconds :
						this.seconds))
					if (this.minutes >= 0) {
						if (this.seconds === 0) { //当倒计时小于0时清除定时器
							this.seconds = 60
							this.minutes--
						}
					} else if (this.minutes < 0) {
						if (this.seconds >= 1) { //当倒计时小于0时清除定时器
							this.block = 0;
							clearInterval(this.clock)
						}
					}
				}, 1000)
			},
			showCardModal: function(e) { //显示答题卡
				console.log(e)
				this.modalCard = e.currentTarget.dataset.target
			},
			hideCardModal: function(e) { //关闭答题卡
				this.modalCard = null
			},
			SwiperChange: function(e) { //滑动事件
				let index = e.target.current;
				if (index != undefined) {
					this.subjectIndex = index;
					this.currentType = this.subjectList[index].type;
					this.userFavor = this.subjectList[index].userFavor;
				}
			},
			RadioboxChange: function(e) { //单选选中
				var values = e.detail.value;
				// console.log(values);
				this.subjectList[this.subjectIndex].userAnswer = values;
				if (this.autoRadioNext && this.subjectIndex < this.subjectList.length - 1) {
					this.subjectIndex += 1;
					this.showAnswer = false;
				};
				this.questions.push(values)
			},

			CheckboxChange: function(e) { //复选选中
				var items = this.subjectList[this.subjectIndex].optionList;
				// console.log(items)
				var values = e.detail.value;
				// console.log("复选" + values)
				this.subjectList[this.subjectIndex].userAnswer = "";
				for (var i = 0, lenI = items.length; i < lenI; ++i) {
					for (var j = 0, lenJ = values.length; j < lenJ; ++j) {
						if (items[i].prefix == values[j]) {
							this.subjectList[this.subjectIndex].userAnswer += items[i].prefix;
							break
						}
					}
				}
				this.questions.push(values)
			},
			// 显示答案
			ShowAnswerChange: function(e) {
				this.showAnswer = !this.showAnswer;
			},
			// 收藏 
			FavorSubject: function(e) {
				if (this.userFavor) {
					this.userFavor = false;
					this.subjectList[this.subjectIndex].userFavor = false;
				} else {
					this.userFavor = true;
					this.subjectList[this.subjectIndex].userFavor = true;
				}
				// 开始收藏，传递的有试题的id，是否收藏
				// console.log(this.subjectList[this.subjectIndex].id)
				// console.log(this.userFavor)
				this.updateFavor(this.subjectList[this.subjectIndex].id,this.userFavor);
			},
			updateFavor(examId,userFavor){
				console.log(examId+":"+userFavor)
				let userId = uni.getStorageSync("user").userId;
				console.log(userId)
				api.updateFavor({
					data:{
						userId:userId,
						examId:examId,
						userFavor:userFavor
					},success:res=>{
						console.log(res)
					},fail:err=>{
						uni.showToast({
							title:"网络延迟",
							icon:"none"
						})
					}
				})
			},
			MoveSubject: function(e) { //上一题、下一题
				// 改变题目，关闭答案
				this.showAnswer = false;
				if (e === -1 && this.subjectIndex != 0) {
					this.subjectIndex -= 1;
				}
				if (e === 1 && this.subjectIndex < this.subjectList.length - 1) {
					this.subjectIndex += 1;
				}
			},
			AppointedSubject: function(e) { //题卡指定
				this.modalCard = null;
				this.subjectIndex = e;
			},
			// 提交，自动判卷，给出分数
			submit: function() {
				let that = this;
				uni.showModal({
					content: '您是否要提交吗?',
					success: function (res) {
						if (res.confirm) {
							// 关闭答题卡
							that.hideCardModal()
							// 显示提交内容
							let wrongList = getScore(that.subjectList).wrongList;
							let score = getScore(that.subjectList).score;
							that.score = score;
							// 自动判卷
							uni.showLoading({
								title:"正在判卷",
								mask:true
							});
							// 收集错题 传递用户id以及试题id
							for(var i = 0;i<wrongList.length;i++){
								that.addUserWrong(wrongList[i])
							}
							setTimeout(function () {
							    uni.hideLoading();
								uni.showModal({
									title:'考试结束',
									content: '本次得分:'+score+'分',
									success: function (res) {
										if (res.confirm) {
											// console.log('用户点击确定');
											that.showAllAnswer = true;
											that.isFinish = true;
										}
									}
								})
							}, 3000);
						}
					}
				})
			},
			// 添加错题与修改
			addUserWrong(examId){
				// console.log(examId)
				let userId = uni.getStorageSync('user').userId;
				api.updateUserWrong({
					data:{
						userId:userId,
						examId:examId,
						isWrong:'true'
					},success:res=>{
						// console.log(res)
					},fail:err=>{
						uni.showToast({
							title:"网络延迟",
							icon:"none"
						})
					}
				})
			},
		},
		watch: {
			// 时钟监听
			content(val) {
				// console.log(val)
				if (val === "0:0") {
					// 如果时间到达，自动提交
					this.block = 0;
					this.submit()
				}
			}
		},
	}
</script>

<style>
	@import "../../colorui/animation.css";
	page {
		background-color: #FFFFFF;
	}
	.topSubmit{
		border-radius: 0.5em;
		width: 115upx;
		box-shadow: 1px 1px 1px 1px red;
		height: 100%;
		text-align: center;
		line-height: 60upx;
		background-color: red;
		font-size: 29upx;
		font-weight: 500;
		color: white;
	}
	.cu-form-group {
		justify-content: flex-start
	}

	.cu-form-group .title {
		padding-left: 30upx;
		padding-right: 0upx;
	}

	.cu-form-group+.cu-form-group {
		border-top: none;
	}

	.cu-bar-title {
		min-height: 50upx;
	}

	.cu-list.menu>.cu-item-error {
		justify-content: flex-start;
	}

	.textarea-height {
		min-height: 600upx;
		font-size: 20px;
		line-height: 20px;
	}

	.block {
		display: block;
	}

	.none {
		display: none;
	}

	.login {
		font-size: 86rpx;
		text-shadow: 5px 5px 5px black, 0px 0px 2px black;
		color: white;
		text-align: center;
	}

	.shadow {
		border-radius: 0.5em;
	}

	.submit {
		margin-left: 50%;
	}

	.eee {
		width: 100%;
	}
</style>