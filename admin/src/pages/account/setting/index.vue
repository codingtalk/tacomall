<template>
	<div class="setting-notice">
		<div class="sn-tab">
			<div class="t-item" :class="{ 't-item-active': tabIdx === 0 }" @click="tabIdx = 0">
				<span>基本设置</span>
			</div>
			<div class="t-item" :class="{ 't-item-active': tabIdx === 1 }" @click="tabIdx = 1">
				<span>安全设置</span>
			</div>
			<div class="t-item">
				<span>消息通知</span>
			</div>
		</div>
		<div class="sn-content">
			<template v-if="tabIdx === 0">
				<div class="sc-title">
					<span>基本设置</span>
				</div>
				<div class="sc-base">
					<div class="sb-form">
						<div class="sf-main">
							<div class="sm-item">
								<div class="si-label">
									<span>姓名</span>
								</div>
								<div class="sel-input">
									<a-input type="email" placeholder="姓名"></a-input>
								</div>
							</div>
							<div class="sm-item">
								<div class="si-label">
									<span>昵称</span>
								</div>
								<div class="sel-input">
									<a-input type="email" placeholder="昵称" :value="$_.get(tenantStaffInfo, 'nickname')"
										v-model="form.nickname"></a-input>
								</div>
							</div>
							<div class="sm-item">
								<div class="si-label">
									<span>个人简介</span>
								</div>
								<div class="sel-input">
									<a-input type="textarea" :rows="5" placeholder="个人简介"></a-input>
								</div>
							</div>
							<div class="sm-item">
								<div class="si-label">
									<span>国家/地图</span>
								</div>
								<div class="sel-input">
									<a-select></a-select>
								</div>
							</div>
							<div class="sm-item">
								<div class="si-label">
									<span>所在省市</span>
								</div>
								<div class="sel-input">
									<a-select></a-select>
									<a-select style="margin-left: 10px"></a-select>
								</div>
							</div>
							<div class="sm-item">
								<div class="si-label">
									<span>街道地址</span>
								</div>
								<div class="sel-input">
									<a-input></a-input>
								</div>
							</div>
							<div class="sm-item">
								<div class="si-label">
									<span>联系电话</span>
								</div>
								<div class="sel-input">
									<a-input></a-input>
									<a-input style="margin-left: 10px"></a-input>
								</div>
							</div>
						</div>
						<div class="sf-avatar">
							<div class="sa-label">
								<span>头像</span>
							</div>
							<div class="sa-input">
								<img src="https://img.codingtalk.cn/5DmNTp51598446051256?x-oss-process=image/resize,m_fill,h_100,w_100"
									alt />
								<a-button style="margin-left: 10px">上传头像</a-button>
							</div>
						</div>
					</div>
					<div class="sb-submit">
						<a-button type="primary" @click.native="update('base')">更新基本信息</a-button>
					</div>
				</div>
			</template>
			<template v-else-if="tabIdx === 1">
				<div class="sc-title">
					<span>安全设置</span>
				</div>
				<div class="sc-safe">
					<div class="s-item">
						<div class="si-label">
							<span>密码</span>
						</div>
						<div class="si-input">
							<a-input placeholder="修改密码" v-model="form.passwd"></a-input>
							<a-button type="primary" style="margin-left: 10px" @click.native="update('safe')">修改
							</a-button>
						</div>
					</div>
					<div class="s-item">
						<div class="si-label">
							<span>邮箱</span>
						</div>
						<div class="si-input">
							<a-input placeholder="修改邮箱"></a-input>
							<a-button type="primary" style="margin-left: 10px">修改</a-button>
						</div>
					</div>
				</div>
			</template>
		</div>
	</div>
</template>

<script>
import {OrgStaff} from "@/entity";
import { isObjHasBlank } from "codingtalk-vue-toolkit";
import { message, Modal } from "ant-design-vue";

export default {
	data() {
		return {
			tabIdx: 0,
			form: {
				nickname: "",
				passwd: "",
			},
		};
	},
	methods: {
		update(s) {
			if (s === "base" && isObjHasBlank(this.form, ["passwd"])) {
				message.warning("请填写信息");
				return;
			}
			if (s === "safe" && !this.form.passwd) {
				message.warning("请填写密码");
				return;
			}
			Modal.confirm({
				title: '提示',
				content: '更新用户信息, 是否继续?',
				onOk: () => {
					OrgStaff.sendApi("staffUpdate", {
						params: {},
						data: this.form,
					}).then((res) => {
						const { status } = res;
						if (status) {
							message.success("更新成功");
						}
					});
				}
			});
		},
	},
};
</script>

<style lang="less">
.setting-notice {
	display: flex;
	padding: 10px 0 40px 0;
	background: white;

	.sn-tab {
		width: 240px;
		border-right: 1px solid #f0f0f0;

		.t-item {
			display: flex;
			align-items: center;
			height: 40px;

			span {
				padding-left: 20px;
			}

			&-active {
				position: relative;
				background: #e6f7ff;
				color: #1890ff;

				&:after {
					position: absolute;
					top: 0;
					right: 0;
					content: "";
					width: 2px;
					height: 100%;
					background: #1890ff;
				}
			}

			&:hover {
				cursor: pointer;
			}
		}
	}

	.sn-content {
		padding-left: 20px;

		.sc-title {
			display: flex;
			align-items: center;
			height: 40px;

			span {
				font-size: 18px;
			}
		}

		.sc-base {
			.sb-form {
				display: flex;

				.sf-main {
					.sm-item {
						.si-label {
							display: flex;
							align-items: center;
							height: 30px;
						}

						.sel-input {
							display: flex;
							align-items: center;
						}

						&:not(:first-child) {
							margin-top: 20px;
						}
					}
				}

				.sf-avatar {
					padding-left: 20px;

					.sa-label {
						display: flex;
						align-items: center;
						height: 30px;
					}

					.sa-input {
						display: flex;
						align-items: center;

						img {
							width: 100px;
							height: 100px;
							border-radius: 50px;
						}
					}
				}
			}

			.sb-submit {
				padding-top: 20px;
			}
		}

		.sc-safe {
			.s-item {
				.si-label {
					display: flex;
					align-items: center;
					height: 30px;
				}

				.si-input {
					display: flex;
					align-items: center;
				}

				&:not(:first-child) {
					margin-top: 20px;
				}
			}
		}
	}
}
</style>