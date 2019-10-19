
学前导读	- Shell(贝壳)
	* Shell,是命令解释器,根据输入的命名执行相应的操作
	* 查看当前系统下有哪些Shell
		* cat /etc/shells
			/bin/sh			[Unix早期支持的是它,已经被bash替代 ]
			/bin/bash		[Ubantu使用,增强版,也就是Linux默认的]
			/bin/nologin
			.. ...
	* 查看当前系统使用什么shell
		* echo $SHELL	
	* 在bash下敲命名的时候,Tab键可以补全已经敲了一部分的文件名和目录名
		* 如果存在多个类似文件,可以摁两下,那么就会把类似的文件都给列出来
	* 切换Shell
		source /exc/bash
――――――――――――――――――――――――――――――――
1,Linux入门-基本命名				|
――――――――――――――――――――――――――――――――
	echo "String"			//回显字符串
	printf "String\n"		//打印字符串,加上\n表示换行
	echo $JAVA_HOME			//echo+$变量,回显变量
	history					//查看历史命令
	su userName				//切换用户
	sudo cmd				//以root身份执行命令
	ctrl + c				//这不是命令,这是摁键.可以终止当前命令
	ctrl + z				//这不是命令,这是摁键.暂停当前命令(线程)
	ctrl + l				//清屏
	clear					//清屏
	exit					//退出终端
	shutdown now   			//关机
	reboot					//重启
	logout					//注销
	pwd						//查看当前所在的目录
	ls						//显示目录列表
	more 文件名				//把文本文件输出到控制台
	cat 文件名				//把文本文件输出到控制台,正序
	tac	文件名				//把文本文件输出到控制台,逆序
	whoami 					//显示我(用户),是谁
	ln -s 源文件　名称		//创建软链接，指向文件节点名称
	ln -l 源文件　名称		//创建硬链接，直接指向硬盘文件
	where 命令				//显示指定命令的文件
	date					//显示和设置日期时间
	id						//显示当前用户的id信息
	local					//显示当前语言环境
	uname					//显示操作系统信息
	dmesg					//显示系统启动信息
	df -hl					//查看硬盘信息

――――――――――――――――――――――――――――――――
2,Linux入门-Shell				|
――――――――――――――――――――――――――――――――
――――――――――――――――――――――――――――――――
3,Linux入门-目录和文件				|
――――――――――――――――――――――――――――――――
	* 绝对路径:从根目录开始描述的路径
	* 相对路径:从当前目录开描述的路径
	* .  表示当前目录
	* .. 表示上一级目录,也就是父目录
	* 在根目录下的.和..,都是根目录
	* Linux下,以.开头的文件就是隐藏文件
	* Ubantu下不同文件显示格式
		* 蓝色:目录
		* 绿色:可执行程序
		* 红色:压缩包
	* 文件详细信息结构
		* drwxr-xr-x 3 kevin kevin 4096 sep 29 09:52 ..
		* 权限 3 文件所有者 所有者用户组 大小 时间 文件名
	* 文件权限信息详解
		* r	:读
		* w	:写
		* x	:执行
		d--- --- ---
		* 第一位代表文件类型
			-:普通文件
			d:目录
			l:符号连接,有点像Windows下的快捷方式
			b:块设备,U盘,硬盘都是
			c:字符设备,鼠标键盘
			s:socket文件,网络套接字
			p:管道
		* 前三位表示自己权限,中三位表示同组用户,后三位表示外组用户
		* root对所有文件,都有所有权限
	ls					//查询出当前目录下所有文件
		-a				//显示出当前目录下所有文件,包括隐藏文件
		-l				//显示出文件的详细信息
		-R				//连同子目录的文件一起列出
		* 他们可以组合写:ls -la			直接显示出所有文件,以及详细信息
	tree 目录名称			//树状显示指定目录的结构，Ubantu默认没装
	pwd					//显示当前所在目录
	cd 目录				//跳转到指定目录
	cd -				//滚回去,哪个目录来,回那个目录去
	pwd ~				//跳转到当前用户目录
	pwd					//查看当前所在路径
	cd ./目录名称			//跳转到当前目录下指定名称的子目录
	cd ../				//跳转到当前目录的父目录
	mkdir 文件名			//在当前文件夹下创建目录
	mkdir -p aa/bb/cc	//创建层级目录
	rmdir 文件夹			//删除指定的空目录
	rm -r 文件夹			//删除指定目录，如果有文件执行递归删除，每个文件都有是否删除的提示
	rm -rf　文件			//强制删除指定文件，不管文件是否存在，不会有任何的是否删除的提示，直接弄死
	rm 文件名			//删除指定文件，如果是目录，而且，目录中有内容，那么会删除失败
	which 				//查看指定命令所在的路径
	touch　文件名			//如果文件不存在，创建一个字节为０的空文件，如果这个文件存在，则把指定文件的修改时间更新为当前时间
	touch ~/文件名		//在用户目录下创建指定名称的文件夹
	cat	文件名			//在命令窗口输出文件内容，如果cat后面没有跟文件名，会把键盘录入输出，直到ctrl+d结束
	more 文件名			//在窗口输出指定文件内容，如果文件过大，可以实现滚动
	head -数量			//只显示文件前指定行数，可以是负数，那么就是倒着来
	mv 文件 名称			//对指定文件进行重命名
	mv 文件 ../			//把指定文件移动到上级目录
	mv 文件 ../名称		//把指定文件移动到上级目录，并且改名
	mv　源文件　目标文件	//把指定文件移动到指定的目录
	more 文件名			//把文本文件内容显示在屏幕上
	cp 源文件　文件２		//把指定文件，以指定名称复制到当前目录下
	cp 源文件　/目录		//把指定文件拷贝到指定目录下
	cp 源目录 目录　-r	//拷贝目录，到指定的目录，注意，要跟上，还是递归拷贝
	ln -s 源文件　名称	//创建软链接，指向文件节点名称
		* 其实，就是这个快捷方式指向的是只是实体文件的引用的引用，它仅有几KB
	ln -l 源文件　名称	//创建硬链接，直接指向硬盘文件
		* 该快捷方式直接指向硬盘文件，直接'指向对象',它的体积就跟实际的文件大小是一样的
	wc　文件名			//统计文件信息，其实就是计数，指定文件有多少行，多少各单词
		-c				//显示文件大小
		-l				//显示行数
	od -tc 文件			//以ASSIC码的形式显示指定文件
		-tcx　文件		//以１６进制显示指定文件
		-tcd 文件		//以１０进制显示指定文件
	du -hm 目录			//查看某个目录的大小，以m为单位，向上取整，不足１Ｍ算１Ｍ
	du -hk 目录			//查看目录大小，以ＫＢ为单位
	df --block-size=GB	//以ＧＢ形式显示磁盘使用情况
	df --block-size=MB	//以ＭＢ形式显示磁盘使用情况
	chmod　u+x 文件		//给指定的文件的所有者，添加一个x权限(执行)
		　　g-w 文件		//给指定文件的同组用户，干掉一个w权限(写)
		　　o-r　文件		//给指定文件的其他人，干掉一个r权限(读)
		　　a+w 文件		//给指定文件，对于所有人＇所有者＇，＇同组＇，＇其他＇都增加一个w权限(写)
		* 其实还有一种数字设定法,可以去百度一下. 
	chown 用户:组 文件	//修改指定文件的所有者,以及所有组,该命令,仅有root用户才能使用
		* 文件所有者,和所有组可以没有关系!所有者不一定是属于所有组
	chown 用户:组 目录 -R //递归操作,把指定目录下的所有文件都变成指定组和指定用户
	chgrp 				//修改文件所有组,用的少,一般都是用上面的
――――――――――――――――――――――――――――――――
4,Linux入门-文件属性用法			|
――――――――――――――――――――――――――――――――
	
――――――――――――――――――――――――――――――――
5,Linux入门-查找和检索				|
――――――――――――――――――――――――――――――――
	find 目录 -name "*.java"			//根据文件名查询指定的文件,允许使用通配符*
	grep "内容" 目录 -R				//根据文件内容在指定的目录进行递归查找,默认不是递归,需要-R参数
	where is 命令					//显示该命令，来自于哪个文件，甚至是帮助命令
――――――――――――――――――――――――――――――――
6,Linux入门-软件安装和卸载			|
――――――――――――――――――――――――――――――――
	* 这是非常重要的一个东西,这里会讲三种在Linux上安装软件的方法
	1,apt-get安装
		* 这东西是Ubantu提供的,Ubantu有一个软件源服务器,在Internet上,所有Ubantu系统都是可以使使用这些服务器
		* Ubantu的软件源服务器,也有非官方的,例如:新浪,搜狐,清华大学
		* 我们需要在Ubantu上安装软件的时候,可以选择指定过服务器,通过网络在服务器上下载安装软件
		* 很显然,这种安装方式是需要联网才能进行
		sudo vi /etc/apt/source.list			//该文件中就有配置源服务器
		sudo apt-get update						//更新源,把源服务器中的软件列表下载到本地,仅仅就是下载个名称和介绍.并非是下载软件本身
			* 一般都是在更新的软件源服务器之后,会操作,获取服务器上的软件列表
		sudo apt-cache search "名称"				//根据指定名称,从本地从服务器上down下来的软件列表中查询
		sudo apt-get install 软件名				//安装指定名称的软件,当然肯定是要有网络的情况下执行
		sudo apt-get install 软件名 --reinstall  //重新安装指定的软件
		sudo apt-get -f install	软件名			//修复安装
		sudo apt-get build-dep 软件名			//安装相关的编译环境
		sudo agt-get remove 软件名				//删除软件
		sudo apt-get remove 软件名 --purge		//删除软件,包括配置文件
		sudo apt-cache show 软件名				//获取软件的相关信息,例如:大小,之类的数据
		sudo apt-get upgrade					//更新已经安装的软件
		sudo apt-get dist-upgrade				//升级系统
		sudo apt-cache depends 软件名			//获取指定软件的依赖信息
		sudo apt-cache rdepends 软件名			//查看指定软件,被哪些软件依赖
		sudo apt-get source 软件名				//下载该软件的源码
	2,deb包安装
		* deb文件格式的安装包
		sudo dpkg -i 名字.deb					//安装指定的软件包
		sudo dpkg -r 名字.deb					//删除指定的软件包
		sudo dpkg -r --purge 名字.deb			//连同配置文件一起删除
		sudo dpkg -info 名字.deb					//查看软件安装包的详细信息
		sudo dpkg -L 名字.deb					//查看文件拷贝详情命令
		sudo dpkg -l							//看到系统中以及安装的软件包
		sudo dpkg-reconfigure 名字				//重新配置软件包
	3,源码安装
		* .h,.c这种软件源代码的安装方式
		* 通常为几个步骤
			1,解压缩源码包
			2,检测文件是否丢失
			3,编译源码,生成库和可执行程序
			4,把库和可执行程序安装到系统路径下
		tar zxbf 源码压缩包名称
		cd 到解压后的路径
		./configure		
		make
		sudo make install
		
――――――――――――――――――――――――――――――――
7,Linux入门-磁盘管理				|
――――――――――――――――――――――――――――――――
	* 磁盘管理非常重要,直接关系到系统的流畅与运行能力
	mount命令
		* U盘的挂载问题,其实系统默认的把U盘挂载到了/media目录下
		* 我们可以手动的进行挂载到指定的目录
		sudo fdisk -l				//查看磁盘信息
		sudo mount 设备文件  目录		//把指定的设备挂载到指定的目录,目录中的文件会被暂时覆盖
		sudo umount 目录				//卸载目录下已经挂载的设备
			* 通常弹出U盘等设备,就可以去默认的挂载目录下,卸载掉相应的挂载就OK
	dd命令
		* 拷贝,对于光盘的拷贝,必须是标砖的ISO9660格式才能进行拷贝操作
		* 也可以拷贝文件,有点像cp
		dd if=/dev/cdrom of=cdrom.iso	//把源文件拷贝到目标文件
			* if:它不是判断,而是指,输入文件
			* of:指的是输出文件
		dd if=源文件 of=目标文件			//把源文件拷贝到目标文件
	
――――――――――――――――――――――――――――――――
8,Linux入门-压缩包管理				|
――――――――――――――――――――――――――――――――
	* 常见的一些压缩包,以及管理手段
	1,tar
		* Linux,Unix下的东西
		tar zcvf name.tar.gz name		//以gzip算法压缩指定的文件
		tar zxvf name.tat name			//以gzip算法解压缩指定的文件
	2,rar
		* 眼熟就对了,window的东西
	3,ZIP
		* 还是windows的东西
		
――――――――――――――――――――――――――――――――
9,Linux入门-进程管理				|
――――――――――――――――――――――――――――――――
	who								//显示终端使用情况
	ps								//查看进程相关信息
		-e							//显示所有进程
		-f							//全格式
		-l							//长格式
		aux							//显示出所有的进程
		ajx
		-Lf 						//查看指定的线程
	jobs 							//显示目前正在运行的作业
	fg 作业编号						//把停止的作业唤醒,在前台(线程)
	bg 作业编号						//把停止的作业唤醒,在后台	
	kill -l							//查看进程编号
		* 一共有64个,前32个信号是属于Unix经典信号,后面32个实时信号,跟硬件开发驱动的时候相关
		*　做开发，一般都是跟前面３２个打交道，除非做驱动开发
		SIGINT,SIGKILL,SIGSEGV,SIGCONT
	kill　信号　进程号					//向指定的进程发送指定的信号，其实９号信号，直接弄死进程
		*　如果不加信号，会默认的发一个．．．
	env								//查看当前环境变量
	
――――――――――――――――――――――――――――――――
10,Linux入门-用户管理				|
――――――――――――――――――――――――――――――――
	* alt+1 ~ 6,分别对应了6个字符控制终端
	* alt+7,就是图形化界面控
	whoami				//我是谁,显示当前用户
	useradd name		//添加一个用户
		-s:指定用户登陆时使用的shell类型
		-g:指定所属组，该组必须存在
		-G:指定附属组，该组必须存在
		-d:用户的家目录
		-m:用户家目录不存在时，自动创建该目录
		* 如果没有指定组，那么就添加一个跟用户名一模一样的组，并把用户添加进去
	passwd 用户名		//为指定用户创建密码，必须是root权限
	su 用户名			//切换用户
	userdel				//删除一个用户
	usermod -g 组 用户名	//修改指定用户的组
	groupadd 组名		//添加一个分组
	groupdel 组名		//删除一个分组
	
――――――――――――――――――――――――――――――――
10,Linux入门-SVN				|
――――――――――――――――――――――――――――――――
	
	llall svnserve							//结束SVN进程
	svnserve -d -r /home/KevinBlandy/SVN/	//开启SVN服务
