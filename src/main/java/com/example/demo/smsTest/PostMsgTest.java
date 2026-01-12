//package com.example.demo.smsTest;
//
//import java.net.URLDecoder;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import com.xuanwu.mos.HostInfo;
//import com.xuanwu.mos.HostInfo.ConnectionType;
//import com.xuanwu.mos.MessageData;
//import com.xuanwu.mos.PostMsg;
//import com.xuanwu.mos.PostMsgBuilder;
//import com.xuanwu.mos.common.entity.Account;
//import com.xuanwu.mos.common.entity.BusinessType;
//import com.xuanwu.mos.common.entity.GsmsResponse;
//import com.xuanwu.mos.common.entity.MOMsg;
//import com.xuanwu.mos.common.entity.MTPack;
//import com.xuanwu.mos.common.entity.MTReport;
//import com.xuanwu.mos.common.entity.TextVarItem;
//import com.xuanwu.mos.common.util.Interceptor;
//import com.xuanwu.mos.common.util.MediaUtil;
//import com.xuanwu.mos.thirdparty.com.google.gson.JsonArray;
//
///**
// * 玄武MOS SDK示例代码
// * @version 5.1
// */
//public class PostMsgTest {
//
//	public static void main(String[] args) throws Exception {
//		final PostMsg pm = PostMsgBuilder.getInstance() // 保持单例模式
//				.setShortConnMode(false) // 设置是否以短连接方式交互, 建议为false(即长连接以提高性能)
//				.setSoTimeout(300000) // Socket读写超时时间(毫秒)
//				.setMaxConnForMT(2) // 每个账号MT最大并发连接数(下发), 若发送量较大可调整为3-7
//				.setMaxConnForMO(2) // 每个账号MO最大并发连接数(获取上行/状态报告)
//				.setEnableSsl(false)
//				.build();
//
//		//pm.getGwHost().setHost("mos.wxchina.com", 9999); // 设置网关的IP和port, 用于发送信息
//		pm.getGwHost().setHost("172.16.22.175", 19081); // 设置网关的IP和port, 用于发送信息
//		//cloud.mosapi.cn
//		//pm.getGwHost().setHost("cloud.mosapi.cn", 19081); // 设置网关的IP和port, 用于发送信息
//		pm.getWsHost().setHost("172.16.22.175", 19070); // 设置网关的IP和port, 用于获取账号信息、上行、状态报告
//
//		// 网络耗时调试功能(仅需要时配置)
//		pm.setInterceptor(new Interceptor() {
//			public void beforeMtSend(long waitConnTime, long loginTime, MTPack pack) {
//				// 加上自定义的日志输出
//				System.out.println("MT before send [" + pack.getBatchID()
//						+ "] wait:" + waitConnTime + ", login:" + loginTime + ", now:" + System.currentTimeMillis());
//			}
//		});
//
//		// 下发失败内部自动重试(仅需要时配置)
//		pm.getGwHost().setRetryTimes(1); // 重试次数
//		pm.getGwHost().setRetryInterval(500); // 重试间隔: 毫秒
//
//		// 设置代理服务器(仅需要时配置)
//		// settingProxyServer(pm);
//
//		Account account = new Account("wsyun@wsyun", "Jbmq9aC9"); // 单账号
//
//		// account.setSessionKey("16位长度的英文或数字密钥"); // 配置通信安全密钥对敏感数据加密(用于高安全性要求的场景, 同时增加少量性能损耗)
//
// 		doSendSms(pm, account); // 短信下行
////		doSendMms(pm, account); // 彩信下行
////		doSendMmsVar(pm,account); //变量彩信下行
////		doGetAccountInfo(pm, account); // 获取账号信息
////		doModifyPwd(pm, account); // 修改密码
//
////		doGetMos(pm, account); // 获取上行信息
//
//	//	doGetReports(pm, account); // 获取状态报告
//		//视频短信模板
////		doSendVarVideo(pm, account);
//		//变量彩信模板
////		doSendMmsTemplate(pm, account);
//	}
//
//	/**
//	 * 短信下发样例, 单次调用最大下发手机号码数为1万。
//	 */
//	public static void doSendSms(PostMsg pm, Account ac) throws Exception {
//		MTPack pack = new MTPack();
//		//pack.setBatchID(UUID.randomUUID());
//		pack.setBatchName("短信测试批次-zjx-mix");
//		pack.setMsgType(MTPack.MsgType.SMS);
//		//pack.setBizType(452887); // 设置业务类型
//		pack.setDistinctFlag(true); // 是否进行号码去重
//		ArrayList<MessageData> msgs = new ArrayList<MessageData>();
//
//		/** 单发, 一号码一内容 */
//		pack.setSendType(MTPack.SendType.GROUP);
//		msgs.add(new MessageData("17610625813", "验证码 123456，您可输入该验证码以重置对接签名秘钥，请勿泄漏。感谢您使用我司为自由职业从业者提供的云结算服务。"));
////		msgs.add(new MessageData("17710816092", "验证码 123456，您可输入该验证码以重置对接签名秘钥，请勿泄漏。感谢您使用我司为自由职业从业者提供的云结算服务。"));
////		msgs.add(new MessageData("17710816092", "验证码 123456，您可输入该验证码以重置对接签名秘钥，请勿泄漏。感谢您使用我司为自由职业从业者提供的云结算服务。"));
////		msgs.add(new MessageData("17710816092", "验证码 123456，您可输入该验证码以重置对接签名秘钥，请勿泄漏。感谢您使用我司为自由职业从业者提供的云结算服务。"));
////		msgs.add(new MessageData("17710816092", "验证码 123456，您可输入该验证码以重置对接签名秘钥，请勿泄漏。感谢您使用我司为自由职业从业者提供的云结算服务。"));
////		msgs.add(new MessageData("17710816092", "验证码 123456，您可输入该验证码以重置对接签名秘钥，请勿泄漏。感谢您使用我司为自由职业从业者提供的云结算服务。"));
//
//		//msgs.add(new MessageData("15014494663", "2AAAaaaaaaaaaBBBAAA【xw】"));
//		//msgs.add(new MessageData("18014494663", "3AAAaaaaaaaaaBBBAAA【xw】"));
//		pack.setMsgs(msgs);
//
//		/** 群发, 多号码同一内容 */
//		/*pack.setSendType(MTPack.SendType.MASS);
//		String content = "短信群发测试";
//		msgs.add(new MessageData("15014494663", content));
//		msgs.add(new MessageData("15014494664", content));
//		msgs.add(new MessageData("15014494665", content));
//		msgs.add(new MessageData("15014494666", content));
//		msgs.add(new MessageData("15014494667", content));
//		pack.setMsgs(msgs);*/
//
//		/** 组发, 多号码多内容 */
//		/*pack.setSendType(MTPack.SendType.GROUP);
//		msgs.add(new MessageData("137000000X1", "短信组发测试1"));
//		msgs.add(new MessageData("137000000X2", "短信组发测试2"));
//		pack.setMsgs(msgs);*/
//
//		/** 使用模板方式发送, 设置模板编号(静态模板将以模板内容发送; 动态模板需要发送变量值) */
//		/*pack.setTemplateNo("8973febf65e144d492d070dc8c55b46c");
//		msgs.add(new MessageData("137000000X1", "[]")); // 1. 静态模板(不需要传变量, 空数组即可)
//		msgs.add(new MessageData("137000000X2", "[\"测试用户\",\"123456\"]")); // 2. 动态模板设置依次替换的参数
//		pack.setMsgs(msgs);
//		*/
//
//		GsmsResponse resp = pm.post(ac, pack);
//		System.out.println(resp);
//	}
//
//	/**
//	 * 发送视频短信
//	 * @param pm
//	 * @param ac
//	 * @throws Exception
//	 */
//	public static void doSendVarVideo(PostMsg pm, Account ac) throws Exception {
//
//        MTPack pack = new MTPack();
//        pack.setBatchName("测试批次");
//        pack.setBizType(452612);
//        pack.setSendType(MTPack.SendType.MASS);
//        //pack.setScheduleTime();
//        pack.setCustomNum("777");
//		pack.setMsgType(MTPack.MsgType.VIDEO);
//        pack.setTemplateNo("12698");
//        pack.setDistinctFlag(true); // 是否进行号码去重
//        ArrayList<MessageData> msgs = new ArrayList<MessageData>();
//
//        MessageData msg = new MessageData("134000000X1", null);
//        JsonArray array = new JsonArray();
//        array.add("var1");
//        array.add("var2");
//        msg.setVariable(array.toString());
//
//        msgs.add(msg);
//
//        pack.setMsgs(msgs);
//
//        GsmsResponse resp = pm.post(ac, pack);
//        System.out.println(resp);
//    }
//
//	/**
//	 * 远程彩信模板下发
//	 */
//	public static void doSendMmsTemplate(PostMsg pm, Account ac) throws Exception {
//		MTPack pack = new MTPack();
//		pack.setBatchID(UUID.randomUUID());
//		pack.setBizType(452612);
//		pack.setBatchName("彩信测试批次-2条");
//		pack.setMsgType(MTPack.MsgType.MMS);
//		pack.setTemplateNo("123456");
//		//pack.setBizType(1);
//		pack.setDistinctFlag(true); // 是否进行号码去重
//		ArrayList<MessageData> msgs = new ArrayList<MessageData>();
//
////		/** 单发，一号码一内容 */
////		msgs.add(new MessageData("134000000X1", null));
////		pack.setMsgs(msgs);
//
//		/** 群发，多号码一内容 */
////		pack.setSendType(MTPack.SendType.MASS);
//		/*msgs.add(new MessageData("18218732294", null));
//		msgs.add(new MessageData("18218732295", null));
//		pack.setMsgs(msgs);*/
//		/** 组发，多号码多内容 */
//		pack.setSendType(MTPack.SendType.GROUP);
//		//设置私有彩信资源
//		MessageData msg1 = new MessageData("134000000X1", null);
//		JsonArray array = new JsonArray();
//		array.add("var1");
//		array.add("var2");
//		msg1.setVariable(array.toString());
//		msgs.add(msg1);
//
//		MessageData msg2 = new MessageData("134000000X2", null);
//		JsonArray array2 = new JsonArray();
//		array2.add("var3");
//		array2.add("var4");
//		msg2.setVariable(array2.toString());
//		msgs.add(msg2);
//
//		pack.setMsgs(msgs);
//		GsmsResponse resp = pm.post(ac, pack);
//		System.out.println(resp);
//	}
//
//	/**
//	 * 彩信下发范例
//	 */
//	public static void doSendMms(PostMsg pm, Account ac) throws Exception {
//		MTPack pack = new MTPack();
//		pack.setBatchID(UUID.randomUUID());
//		pack.setBatchName("彩信测试批次-2条");
//		pack.setMsgType(MTPack.MsgType.MMS);
//		//pack.setBizType(1);
//		pack.setDistinctFlag(true); // 是否进行号码去重
//		ArrayList<MessageData> msgs = new ArrayList<MessageData>();
//
//		String path = PostMsgTest.class.getClassLoader().getResource("mms_test").getPath();
//		path = URLDecoder.decode(path, "utf-8");
//
//		// 设置公共彩信资源
//		pack.setMedias(MediaUtil.getMediasFromFolder(path));
//
////		/** 单发，一号码一内容 */
////		msgs.add(new MessageData("134000000X1", null));
////		pack.setMsgs(msgs);
//
//		/** 群发，多号码一内容 */
////		pack.setSendType(MTPack.SendType.MASS);
//		/*msgs.add(new MessageData("18218732294", null));
//		msgs.add(new MessageData("18218732295", null));
//		pack.setMsgs(msgs);*/
//
//		/** 组发，多号码多内容 */
//		pack.setSendType(MTPack.SendType.GROUP);
//		//设置私有彩信资源
//		MessageData msg1 = new MessageData("134000000X1", null);
//		msg1.setMedias(MediaUtil.getMediasFromFolder(path));
//		msgs.add(msg1);
//
//		MessageData msg2 = new MessageData("134000000X2", null);
//		msg2.setMedias(MediaUtil.getMediasFromFolder(path));
//		msgs.add(msg2);
//
//		pack.setMsgs(msgs);
//		GsmsResponse resp = pm.post(ac, pack);
//		System.out.println(resp);
//	}
//
//    /**
//     * 彩信变量文本下发范例
//     */
//    public static void doSendMmsVar(PostMsg pm, Account ac) throws Exception {
//        MTPack pack = new MTPack();
//        pack.setBatchID(UUID.randomUUID());
//        pack.setBatchName("变量彩信测试批次-2条");
//        pack.setMsgType(MTPack.MsgType.MMS);
//        pack.setDistinctFlag(true); // 是否进行号码去重
//        ArrayList<MessageData> msgs = new ArrayList<MessageData>();
//
//        // 指定读取文本TXT 路径
//        String path = PostMsgTest.class.getClassLoader().getResource("mms_test_var/").getPath();
//        pack.setSendType(MTPack.SendType.GROUP);
//
//        // 构建短信内容
//        MessageData msg2 = new MessageData("18218732283", null);
//        TextVarItem textVarItem = new TextVarItem(); // 变量文本内容对象
//        textVarItem.putVar("subject.txt", "msg2-标题1");
//        textVarItem.putVar("text1.txt", "msg2-文本1","msg2-文本22");
//        textVarItem.putVar("text3.txt", "msg2-变量");
//        textVarItem.putVar("NoExistsFile.txt", "msg2-变量&&");
//        msg2.setMedias(MediaUtil.getMediasFromFolder(path, textVarItem));
//        msgs.add(msg2);
//
//        MessageData msg3 = new MessageData("18218732285", null);
//        TextVarItem textVarItem2 = new TextVarItem();
//        textVarItem2.putVar("subject.txt", "msg3-test1");
//        textVarItem2.putVar("text1.txt", "msg3-测试1","msg3-test22");
//        textVarItem2.putVar("text3.txt", "msg3-变量");
//        msg3.setMedias(MediaUtil.getMediasFromFolder(path, textVarItem2));
//        msgs.add(msg3);
//
//        pack.setMsgs(msgs);
//        GsmsResponse resp = pm.post(ac, pack);
//        System.out.println(resp);
//    }
//
//	/**
//	 * 获取账号信息
//	 */
//	public static void doGetAccountInfo(PostMsg pm, Account ac) throws Exception {
//		System.out.println(pm.getAccountInfo(ac));   // 获取账号详细信息
//
//		List<BusinessType> bizTypes = pm.getBizTypes(ac); // 获取账号绑定业务类型
//		for (BusinessType bizType : bizTypes) {
//			System.out.println(bizType);
//		}
//	}
//
//	/**
//	 * 获取上行信息
//	 */
//	public static void doGetMos(PostMsg pm, Account ac) throws Exception {
//		List<MOMsg> moList = pm.getMOMsgs(ac, 100);
//		if (moList != null && moList.size() > 0) {
//			String confirmId = null;
//			for (MOMsg mo : moList) {
//				System.out.println(mo);
//				confirmId = mo.getReserve();
//			}
//
//			// 数据确认, 当数据处理完毕需要发送确认回执, 否则会重复获取
//			if (!pm.isAutoConfirm()) {
//				pm.confirmMoRequest(ac, confirmId);
//			}
//		}
//	}
//
//	/**
//	 * 获取状态报告
//	 */
//	public static void doGetReports(PostMsg pm, Account ac) throws Exception {
//		List<MTReport> reportList = pm.getReports(ac, 1);
//		if (reportList != null && reportList.size() > 0) {
//			String confirmId = null;
//			for (MTReport report : reportList) {
//				System.out.println(report);
//				confirmId = report.getReserve();
//			}
//
//			// 数据确认，当数据处理完毕，需要发送确认回执，否则会重复获取
//			if (!pm.isAutoConfirm()) {
//				pm.confirmReportRequest(ac, confirmId);
//			}
//		}
//	}
//
//	/**
//	 * 修改密码
//	 */
//	public static void doModifyPwd(PostMsg pm, Account ac) throws Exception {
//		System.out.println(pm.modifyPassword(ac, "你的复杂密码"));
//	}
//
//	/**
//	 * 设置代理服务器
//	 */
//	public static void settingProxyServer(PostMsg postMsg) {
//		HostInfo proxyHost = postMsg.getProxy().getProxyHost();
//		proxyHost.setType(ConnectionType.SOCKET5);
//		proxyHost.setHost("192.168.70.129", 1080);
//		//proxyHost.setUsername("admin");
//		//proxyHost.setPassword("123456");
//	}
//
//}
