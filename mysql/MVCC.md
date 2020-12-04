# 1. MySQL 中的事务

MySQL 提供了两种事务型的存储引擎：InnoDB 和 NDB Cluster 。另外还有一些第三方存储引擎也支持事务

<!-- TOC -->

- [1. MySQL 中的事务](#1-mysql-中的事务)
    - [1.1. 自动提交(AUTOCOMMIT)](#11-自动提交autocommit)
    - [1.2. 在事务中混用存储引擎](#12-在事务中混用存储引擎)
- [2. 多版本并发控制(MVCC)](#2-多版本并发控制mvcc)
    - [2.1. InnoDB 的MVCC](#21-innodb-的mvcc)

<!-- /TOC -->

## 1.1. 自动提交(AUTOCOMMIT)

MySQL 默认采用自动提交模式。也就是说每个查询都被当作一个事务执行提交操作，可以设置`AUTOCOMMIT` 变量来启用或者禁止自动提交模式：
```
# 查询当前的模式
 show variables like 'AUTOCOMMIT'

# 禁用自动提交
SET AUTOCOMMIT = 0;
```
当 `AUTOCOMMIT=0`时，所有的查询都是在一个事务中，直到显示的执行 `COMMIT` 或者 `ROLLBACK`
`AUTOCOMMIT` 对非事务存储引擎不会有任何影响

## 1.2. 在事务中混用存储引擎

MySQL 在服务层不管理事务，事务由下层的存储引擎实现，所以在不同的存储引擎中处理同一个事务是不可靠的。
例如： table_a 使用 innodb 引擎， table_b  使用 MyISAM 引擎(不支持事务)
假设以下操作在一个事务中：
```
insert table_a ...    # 1
insert table_b ...    # 2
update table_a ...    # 3
```

假设在 执行 # 3 时出现了异常，这时事务要回滚，因为 table_b 并不支持事务，这就导致 table_b 的修改无法回滚，导致违反事务的 一致性 和 原子性


# 2. 多版本并发控制(MVCC)

MySQL 大部分事务型存储引擎并不是简单的行级锁。基于提升并发行的考虑，它们一般都同时实现了多版本并发控制 MVCC.

## 2.1. InnoDB 的MVCC

InnoDB 的 MVCC ，是通过在每行记录后面保存两个隐藏的列来实现的。这两个列一把保存了行的创建时间，一个保存行的过期时间(或删除时间)，当然存储的并不是真正的时间，而是系统版本号。每开始一个事务，系统版本号就会自动递增，事务开始时刻的版本号作为当前事务的版本号，用来和查询到的每行记录的版本号就行比较。

以下是 REPEATABLE READ 的隔离级别下具体操作：

- SELECT

    InnoDB 会根据以下两个条件检查每行记录：

    a. InnoDB 只查询版本早于当前事务版本的数据行（也就是，行的系统版本号小于或等于事务的系统版号），这样可以确保事务读取的行，要么是在事务开始前的已经存在的，要么是事务自身插入或者修改过的。
    b. 行的删除版本要么未定义，要么大于当前事务版本号。这可以确保事务读取到的行，在事务开始之前未被删除。

    只有符合上述两个条件的记录，才能返回作为查询结果
- INSERT

    InnoDB 为新插入的每一行保存当前系统版本号作为行版本号

- DELETE

    InnoDB 为删除的每一行保存当前系统版本号作为行删除标识

- UPDATE
    InnoDB 为插入一行新记录，保存当前系统版本号作为行版本号，同时保存当前系统版本号到原来的行作为行删除标识

保存着两个额外的系统版本号，使大多数读操作都可以不用加锁。这样设计使得读数据操作很简单，性能很好，并且也能保证只会读取到符合标准的行