demo是三种实现底部导航栏的方式,
	第一种是LinearLayout+textView+fragment实现,
		通过点击layout切换fragment实现
		优点:实现原理简单,逻辑清晰,可以自定义角标并显示数量
		缺点:由于没加ViewPager,所以不能左右滑动切换
		
	第二种是RadioButton+fragement实现,
		使用RadioGroup+RadioButton实现底部导航栏
		优点:逻辑简单
		缺点:无法显示右上角标和信息数量,也不太好调整UI因为RadioButton不好设置图片大小
		所以看起来相当丑
		
	第三种是使用google开发的ButtonNavigationBar+fragment实现
		优点,使用简单,无需开发者定义控件UI了,点击还有动画效果
		缺点:背景不好设置,无法自定义UI效果
	
	
	
	
	note:
	1:RadioButton不好设置view大小,看起来很丑
	2:google开发的ButtonNavigationBar一些点击特效和背景无法去掉,不好自定义