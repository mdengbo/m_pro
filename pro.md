#### m-pro 依赖总览
> m_pro
>> m-ability:
> 
>>m-business: 核心业务梳理模块，pom依赖: m-commons-web, m-commons-core, m-config
>>> + 1、按不同业务模块分类依赖各自pom
>>>> + 1、 m-business-student-core  pom依赖：m-business-student-protocol，m-data-mapper-student
>>>> + 2、 m-business-student-feign
>>>> + 3、 m-business-student-feign）
> 
>> m-commons: 通用基础模块，pom依赖 m-data-base
>>> m-commons-core: 通用核心模块
> >
>>> m-commons-mysql: mysql数据库模块
> >
>>> m-commons-web: web通用核心模块
>>> + 1、apiversion 多版本管理: 输系统和多版本接口
>>> + 2、config 依赖注入管理（配和 spring.factories 管理使用）
>>> + 3、exception 统一异常处理
>>> + 4、自定义数据校验处理
> 
> >m-config: 统一配置信息管理（prod, dev, test 多环境配置）
> > + 1、resources 基础环境配置
> > + 2、resources-dev 开发环境
> > + 3、resources-file 日志(log4j2)类信息配置
> > + 4、resources-test 测试环境
> 
> > m-data: 实体模块
> > > m-data-base: 基本实体管理（base：共用实体父类，page：分页基础返回类， r：统一返回实体类）
> >
> > > m-data-entity: 数据库实体类管理
> > > > + 1、按照不同业务类模块区分 eg: m-data-entity-student
> >
> > > m-data-mapper: 数据库接口dao管理
> > > > + 1、按照不同业务类模块区分 eg：m-data-mapper-student