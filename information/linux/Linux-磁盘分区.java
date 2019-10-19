――――――――――――――――――――――――――――――――
1,Linux管理分区					|
――――――――――――――――――――――――――――――――
	* Linux管理硬件跟Window完全不同
	* Linux没有C盘,D盘,E盘这些东西,它只有最根的一个目录就叫做"/"
	* 在Linux下,所有的设备,任何东西!在Linux看来,都是文件
	* 根据设备的不同又可以额分为块设备，字符设备
		* 键盘,字符设备
		* U盘,块设备
	* Linux管理硬件的目录就是:/dev
	* Linux分区的命名
		* 分区名的前面两个字母标明分区所在设备的类型．通常是hd(IDE磁盘)，sd(SCSI磁盘)
		．．．
――――――――――――――――――――――――――――――――
1,Linux磁盘分区和mount point挂载点	|
――――――――――――――――――――――――――――――――
	* 很多人刚学Linux就就会被这个弄成傻逼，Window中每一个分区都有一个驱动器字母来表示相应的磁盘目录
	* Linux处理分区以及磁盘存储问题的方法截然不同，其中主要的区别在于，Linux中的每一个分区都是构成支持一组文件
	　和目录所必须的存储区的一部分，它是通过挂载(mounting)来实现的，挂载是把分区关联到某一个目录的过程
	　挂载分区使用起始于这个指定目录，统称为－挂载点，Mmount point，的存储区能够被使用

	* 图解
		系统				主分区				扩展分区	
		windows			C					D				E				F
		Linux			/dev/hda1			/dev/hda2
											|--/dev/hda5		/dev/hda6		/dev/hda7
		* 没记错的话，扩展分区最多只有四个，因为硬盘的分区表只能记录四个分区信息，但是每个扩展分区又可以有n个逻辑分区

	
