<template>
	<div class="page account_profile">
		<div class="profile_left">
			<div class="l-profile">
				<div class="p-avatar">
					<img src="/image/avatar-default.png" alt />
				</div>
				<div class="p-name">
					<span>{{ $_.get(user, "nickname") }}</span>
				</div>
				<div class="p-saying">
					<p>这个人很懒，什么也没有留下~~</p>
				</div>
				<div class="p-grid">
					<div class="g-item">
						<div class="i-left">
							<i class="iconfont icon-bumen"></i>
						</div>
						<div class="i-right">
							<span>{{ $_.get(user, "orgDept.name") }}</span>
						</div>
					</div>
					<div class="g-item">
						<div class="i-left">
							<i class="iconfont icon-gangwei"></i>
						</div>
						<div class="i-right">
							<span>{{ $_.get(user, "orgJob.name") }}</span>
						</div>
					</div>
					<div class="g-item">
						<div class="i-left">
							<i class="iconfont icon-weixin"></i>
						</div>
						<div class="i-right">
							<span>13680065830</span>
						</div>
					</div>
					<div class="p-tag">
						<div class="t-title">
							<span>标签</span>
						</div>
						<div class="t-main">
							<span>996践行者</span>
							<span>摸鱼</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="profile_right">
			<div class="r-content">
				<div class="c-tab">
					<div class="t-item t-item-active">
						<span>工作动态</span>
					</div>
					<div class="t-item">
						<span>登录日志</span>
					</div>
				</div>
				<div class="c-main">
					<div class="m-list m-list-moment">
						<div class="a-item" :key="key" v-for="(item, key) in 1">
							<div class="i-title">
								<h3>月检审核</h3>
							</div>
							<div class="i-tag">
								<span>紧急</span>
								<span>审核中</span>
							</div>
							<div class="i-desc">
								<p>
									项目：深未大厦 月份: 5月 类型：安全月检 创建人：超级管理员
								</p>
							</div>
							<div class="i-author">
								<div class="a-avatar">
									<img
										src="https://img.codingtalk.cn/5DmNTp51598446051256?x-oss-process=image/resize,m_fill,h_100,w_100"
										alt />
								</div>
								<div class="a-text">
									<span>超级管理员</span>
									<span>发表于 2022-05-08 12:23</span>
								</div>
							</div>
							<div class="i-do">
								<div class="d-item">
									<i class="iconfont icon-like"></i>
									<span>12</span>
								</div>
								<div class="d-item">
									<i class="iconfont icon-pinglun"></i>
									<span>154</span>
								</div>
							</div>
						</div>
					</div>
					<div class="m-more">
						<div class="m-wrap">
							<span>暂无更多</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script setup>
import { storeToRefs } from 'pinia';
import useAppStore from '@/store/app';

const appStore = useAppStore();

let { user } = storeToRefs(appStore);

</script>

<style lang="less">
.account_profile {
	display: flex;

	.profile_left {
		width: 400px;

		.l-profile {
			display: flex;
			flex-direction: column;
			align-items: center;
			padding: 16px;
			background: white;
			border-radius: 3px;

			.p-avatar {
				width: 100px;
				height: 100px;
				border-radius: 50px;
				overflow: hidden;

				img {
					width: 100%;
					height: 100%;
				}
			}

			.p-name {
				padding-top: 16px;

				span {
					font-size: 20px;
				}
			}

			.p-saying {
				padding-top: 16px;

				p {
					padding: 0 30px;
					word-break: break-all;
					text-align: center;
				}
			}

			.p-grid {
				width: 100%;
				padding-top: 16px;

				.g-item {
					display: flex;
					align-items: center;
					height: 36px;

					.i-left {
						width: 25px;

						i {
							font-size: 20px;
						}
					}

					.i-right {
						flex: 1;
					}
				}
			}

			.p-link {
				display: flex;
				align-items: center;
				padding: 16px 24px;

				.l-item {
					display: flex;
					justify-content: center;
					flex: 1;

					a {
						img {
							width: 32px;
							height: 32px;
						}
					}
				}
			}

			.p-tag {
				border-top: 1px dashed #808695;
				background: white;

				.t-title {
					display: flex;
					align-items: center;
					height: 60px;
				}

				.t-main {
					display: flex;
					flex-wrap: wrap;
					padding: 20px 0;
					min-height: 100px;

					span {
						display: inline-block;
						height: 20px;
						line-height: 20px;
						padding: 0 10px;
						margin: 0 20px 20px 0;
						border-radius: 2px;
						background: #f5f7f9;
					}
				}
			}
		}
	}

	.profile_right {
		flex: 1;
		margin-left: 20px;

		.r-content {
			background: white;

			.c-tab {
				display: flex;
				height: 50px;

				.t-item {
					display: flex;
					align-items: center;
					flex-grow: 0;
					padding: 0 20px;

					&-active {
						border-bottom: 2px solid #2d8cf0;
						color: #2d8cf0;
					}

					&:hover {
						cursor: pointer;
					}
				}
			}

			.c-main {
				.m-list {
					padding: 20px;

					&-moment {
						.a-item {
							.i-title {
								margin-bottom: 12px;
								line-height: 24px;

								h3 {
									font-size: 16px;
									color: #31445b;
								}
							}

							.i-tag {
								display: flex;
								align-items: center;
								margin-bottom: 12px;

								span {
									display: inline-block;
									height: 20px;
									line-height: 20px;
									padding: 0 10px;
									margin-right: 20px;
									border-radius: 2px;
									font-size: 12px;
									background: #f5f7f9;
								}
							}

							.i-desc {
								margin-bottom: 12px;

								p {
									font-size: 14px;
								}
							}

							.i-author {
								display: flex;
								align-items: center;
								margin-bottom: 12px;

								.a-avatar {
									width: 24px;
									height: 24px;
									border-radius: 12px;
									overflow: hidden;

									img {
										width: 24px;
										height: 24px;
									}
								}

								.a-text {
									display: flex;
									align-items: center;
									padding-left: 10px;

									span {
										font-size: 12px;

										&:not(:first-child) {
											padding-left: 10px;
										}
									}
								}
							}

							.i-do {
								display: flex;

								.d-item {
									display: flex;
									align-items: center;
									padding: 0 10px;

									i {
										font-size: 18px;
									}

									span {
										font-size: 12px;
									}

									&:not(:first-child) {
										border-left: 1px solid #f5f7f9;
									}
								}
							}
						}
					}
				}

				.m-more {
					padding: 20px;

					.m-wrap {
						display: flex;
						align-items: center;
						justify-content: center;
						height: 30px;
						background: #f4f5f6;

						&:hover {
							cursor: pointer;
						}
					}
				}
			}
		}
	}
}
</style>