<#assign base = request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mybatis Builder</title>

    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/vue.js"></script>
    <script src="/js/qs.min.js"></script>
    <link href="/css/style.css" rel="stylesheet" />
    <link href="${base}/css/bootstrap.min.css" rel="stylesheet" />

</head>
<body >
<div id="app">
<nav class="navbar navbar-inverse navbar-fixed-top site-navbar">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="./">MyBatis Builder</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">

                <li><a href="https://github.com/huzhicheng/kite-mybatis-builder/blob/master/README.md">关于</a>
                </li>

            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</nav>

<header class="jumbotron">
    <p>
        <button type="button" class="btn btn-primary" @click="newProject" data-toggle="modal" data-target="#newProjectModal">新建项目</button>
    </p>
</header>
<div class="list-zone center-block">
    <div class="panel panel-default" v-for="config in configList">
        <div class="panel-heading">{{config.projectName}}
            <button type="button" class="btn btn-primary zone-right" @click="editConfig(config.id)" data-toggle="modal" data-target="#editProjectModal">开始生成</button>
        </div>
        <div class="panel-body">
            <div class="form-group">
                <label>数据库连接串:</label>
                <label>{{ config.connectionURL }}</label>
            </div>

        </div>
    </div>
</div>

<!-- 新建项目 -->
<div class="modal fade project-model"  id="newProjectModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg"   role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">新项目</h4>
            </div>
            <div class="modal-body">
                <form class="step1" v-if="step1Visible" v-model="step1Form">

                    <div class="row">
                        <div class="form-group col-md-12 form-inline">
                            <label>项目名称</label>
                            <input type="text" class="form-control" v-model="step1Form.projectName" name="projectName" id="projectName" placeholder="项目名称">

                        </div>

                    </div>

                    <div class="row">
                        <div class="form-group col-md-6 form-inline">
                            <label>targetRuntime</label>
                            <select class="form-control" v-model="step1Form.targetRuntime" name="targetRuntime">
                                <option v-for="item in targetRuntimeList">
                                    {{ item }}
                                </option>
                            </select>
                        </div>
                        <div class="form-group col-md-6 form-inline">
                            <label>是否取消注释</label>
                            <input type="checkbox" v-model="step1Form.suppressAllComments" name="suppressAllComments" id="suppressAllComments">
                        </div>

                    </div>
                    <div class="row">
                        <div class="form-group col-md-6 form-inline">
                            <label>targetProject</label>
                            <input type="text" class="form-control" v-model="step1Form.targetProject" name="targetProject" id="targetProject" placeholder="生成目录">
                        </div>

                        <div class="form-group col-md-6 form-inline">
                            <label>实体类包名</label>
                            <input type="text" class="form-control" v-model="step1Form.targetModelPackage" name="targetModelPackage" id="targetModelPackage" placeholder="实体类包名称">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-6 form-inline">
                            <label>*mapper.xml位置</label>
                            <input type="text" class="form-control" v-model="step1Form.targetMapperPackage" name="targetMapperPackage" id="targetMapperPackage" placeholder="xml文件所在包">
                        </div>

                        <div class="form-group col-md-6 form-inline">
                            <label>mapper接口包名</label>
                            <input type="text" class="form-control" v-model="step1Form.targetMapperInterfacePackage" name="targetMapperInterfacePackage" id="targetMapperInterfacePackage" placeholder="mapper接口包名">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-6 form-inline">
                            <label>数据库驱动</label>
                            <select class="form-control" v-model="step1Form.driverClass" name="driverClass">
                                <option v-for="item in driverClassList">
                                    {{item}}
                                </option>
                            </select>
                          </div>


                    </div>
                    <div class="row">
                        <div class="form-group col-md-12 form-inline">
                            <label>数据库连接字符串</label>
                            <input type="text" class="form-control long-input" v-model="step1Form.connectionURL" name="connectionURL" placeholder="数据库连接字符串">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-6 form-inline">
                            <label>数据库用户</label>
                            <input type="text" class="form-control" v-model="step1Form.userId" name="userId" placeholder="数据库用户">
                        </div>

                        <div class="form-group col-md-6 form-inline">
                            <label>数据库用户密码</label>
                            <input type="password" class="form-control" v-model="step1Form.password" name="password" placeholder="">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-6 form-inline">
                            <label>是否覆盖</label>
                            <input type="checkbox" v-model="step1Form.overwrite">
                        </div>

                        <div class="form-group col-md-6 form-inline">
                            <label>去掉前缀</label>
                            <input type="text" placeholder="例如：w_" v-model="step1Form.removePrefix" >
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" id="btn-next" @click="saveAndNext" class="btn btn-primary">下一步</button>
                    </div>
                </form>

                <form class="step2" v-if="step2Visible">

                    <ul class="list-group">
                        <li class="list-group-item">
                            <input type="checkbox" value="全选" v-model="isSelectAll" @change="selectedAll"/>全选 </li>
                        <li v-for="(item,index) in tables" class="list-group-item form-inline">

                            <div class="form-group my-group">
                                <input type="checkbox"  :id="index" v-model="newSelectedList[index]"/>
                                <span class="label label-info">{{item.tableName}}</span>
                            </div>
                            <div class="form-group">
                                <span>对应的实体名称：</span>
                                <input type="text" class="form-control" v-model="item.entityName"/>
                            </div>

                        </li>
                    </ul>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" @click="build" data-toggle="modal" data-target="#resultModal" class="btn btn-primary">生成</button>
                    </div>
                </form>

                <#--<form v-if="isShowResult">-->
                    <#--<span :class="resultClass">{{ buildResult }}</span>-->
                    <#--<div class="well well-lg">-->
                        <#--<ul class="list-group">-->
                            <#--<li v-for="item in waringList" class="list-group-item">-->
                                <#--{{ item }}-->
                            <#--</li>-->
                        <#--</ul>-->
                    <#--</div>-->

                    <#--<div class="modal-footer">-->
                        <#--<button type="button" class="btn btn-default" @click="closeResult" data-dismiss="modal">关闭</button>-->
                    <#--</div>-->
                <#--</form>-->
            </div>

        </div>
    </div>
</div>


<!-- 编辑项目 根据项目生成-->
<div class="modal fade project-model"  id="editProjectModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">项目配置</h4>
            </div>
            <div class="modal-body">
                <form class="step1" v-if="editStep1Visible" v-model="editForm">

                    <div class="row">
                        <div class="form-group col-md-12 form-inline">
                            <label>项目名称</label>
                            <input type="text" class="form-control" v-model="editForm.projectName" placeholder="项目名称">

                        </div>

                    </div>

                    <div class="row">
                        <div class="form-group col-md-6 form-inline">
                            <label>targetRuntime</label>
                            <select class="form-control" v-model="editForm.targetRuntime">
                                <option v-for="item in targetRuntimeList">
                                    {{ item }}
                                </option>
                            </select>
                        </div>
                        <div class="form-group col-md-6 form-inline">
                            <label>是否取消注释</label>
                            <input type="checkbox" v-model="editForm.suppressAllComments">
                        </div>

                    </div>
                    <div class="row">
                        <div class="form-group col-md-6 form-inline">
                            <label>targetProject</label>
                            <input type="text" class="form-control" v-model="editForm.targetProject" placeholder="生成目录">
                        </div>

                        <div class="form-group col-md-6 form-inline">
                            <label>实体类包名</label>
                            <input type="text" class="form-control" v-model="editForm.targetModelPackage" placeholder="实体类包名称">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-6 form-inline">
                            <label>mapper xml 包</label>
                            <input type="text" class="form-control" v-model="editForm.targetMapperPackage" placeholder="xml文件所在包">
                        </div>

                        <div class="form-group col-md-6 form-inline">
                            <label>mapper接口包名</label>
                            <input type="text" class="form-control" v-model="editForm.targetMapperInterfacePackage" placeholder="mapper接口包名">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-6 form-inline">
                            <label>数据库驱动</label>
                            <select class="form-control" v-model="editForm.driverClass">
                                <option v-for="item in driverClassList">
                                    {{item}}
                                </option>
                            </select>
                        </div>


                    </div>
                    <div class="row">
                        <div class="form-group col-md-12 form-inline">
                            <label>数据库连接字符串</label>
                            <input type="text" class="form-control long-input" v-model="editForm.connectionURL" placeholder="数据库连接字符串">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-6 form-inline">
                            <label>数据库用户</label>
                            <input type="text" class="form-control" v-model="editForm.userId" placeholder="数据库用户">
                        </div>

                        <div class="form-group col-md-6 form-inline">
                            <label>数据库用户密码</label>
                            <input type="password" class="form-control" v-model="editForm.password" placeholder="">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-6 form-inline">
                            <label>是否覆盖</label>
                            <input type="checkbox" v-model="editForm.overwrite" >
                        </div>
                        <div class="form-group col-md-6 form-inline">
                            <label>去掉前缀</label>
                            <input type="text" placeholder="例如：w_" v-model="editForm.removePrefix" >
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" @click="confirmAndNext" class="btn btn-primary">下一步</button>
                    </div>
                </form>

                <form class="step2" v-if="editStep2Visible">

                    <ul class="list-group">
                        <li class="list-group-item">  <input type="checkbox" value="全选" v-model="isEditSelectAll" @change="editSelectedAll"/>全选 </li>
                        <li v-for="(item,index) in tables" class="list-group-item form-inline">

                                <div class="form-group my-group">
                                    <input type="checkbox"  :id="index" v-model="editSelectedList[index]"/>
                                    <span class="label label-info">{{item.tableName}}</span>
                                </div>
                                <div class="form-group">
                                    <span>对应的实体名称：</span>
                                    <input type="text" class="form-control" v-model="item.entityName"/>
                                </div>

                        </li>
                    </ul>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" @click="editBuild" data-toggle="modal" data-target="#resultModal" class="btn btn-primary">生成</button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>

<div class="fade modal" id="resultModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <#--<div class="modal-header">-->
                    <#--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>-->
                    <#--<h4 class="modal-title" id="myModalLabel">Modal title</h4>-->
                <#--</div>-->
                <div class="modal-body">
                    <span :class="resultClass">{{ buildResult }}</span>
                    <div class="well well-lg" v-if="waringList">
                        <ul class="list-group">
                            <li v-for="item in waringList" class="list-group-item">
                                {{ item }}
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" @click="closeResult" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
<script type="text/javascript">

    var app = new Vue({
        el: '#app',
        data: function () {
            return{
                step1Visible:true,
                step2Visible:false,
                editStep1Visible:true,
                editStep2Visible:false,
                targetRuntimeList:['MyBatis3','MyBatis3Simple','Ibatis2Java2','Ibatis2Java5'],
                driverClassList:['com.mysql.jdbc.Driver'],
                step1Form:{
                    id:0,
                    projectName:'',
                    targetRuntime:'MyBatis3',
                    suppressAllComments:false,
                    targetProject:'',
                    targetModelPackage:'org.test.dao.entity',
                    targetMapperPackage:'mapper',
                    targetMapperInterfacePackage:'org.test.mapper',
                    driverClass:'com.mysql.jdbc.Driver',
                    connectionURL:'jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8',
                    userId:'',
                    password:'',
                    overwrite:true,
                    removePrefix:''
                },
                editForm:{
                    id:0,
                    projectName:'',
                    targetRuntime:'MyBatis3',
                    suppressAllComments:false,
                    targetProject:'',
                    targetModelPackage:'',
                    targetMapperPackage:'',
                    targetMapperInterfacePackage:'',
                    driverClass:'',
                    connectionURL:'',
                    userId:'',
                    password:'',
                    overwrite:true,
                    removePrefix:''
                },
                configList:[],
                tables:[],
                buildTables:[],
                isSelectAll:false,
                isEditSelectAll:false,
                newSelectedList:[],
                editSelectedList:[],
                buildResult:'',
                resultClass:'',
                isShowResult:false,
                waringList:[]
            }
        },
        methods: {
            newProject(){
                this.step1Visible = true;
                this.step2Visible = false;
            },
            saveAndNext(){
                var self = this;
                this.step1Visible = false;
                this.step2Visible = true;
                this.newSelectedList = []
                var data = Qs.stringify(self.step1Form);
                $.ajax({
                    type:"get",
                    url:"/createConfig",
                    data:data,
                    success:function (result) {
                        self.step1Form.id = result.id;
                        self.initTables(self.step1Form.id);
                    }
                });
            },
            initEditTables(id){
                var self = this;
                $.ajax({
                    type:"get",
                    url:"/getTables",
                    data:{"id":id,"removePrefix":self.editForm.removePrefix},
                    success:function (result) {
                        self.tables = result;
                        for(var i = 0;i<self.tables.length;i++){
                            self.editSelectedList.push(false);
                        }
                    }
                });
            },
            initTables(id){
                var self = this;
                $.ajax({
                    type:"get",
                    url:"/getTables",
                    data:{"id":id,"removePrefix":self.step1Form.removePrefix},
                    success:function (result) {
                        self.tables = result;
                        for(var i = 0;i<self.tables.length;i++){
                            self.newSelectedList.push(false);
                        }
                    }
                });
            },
            setCheckboxesStatus(status){
                var self = this;
                for(var i = 0; i<self.newSelectedList.length; i++){
                    self.newSelectedList[i] = status;
                }
            },
            setEditCheckboxesStatus(status){
                var self = this;
                for(var i = 0; i<self.editSelectedList.length; i++){
                    self.editSelectedList[i] = status;
                }
            },
            initConfigList(){
                var self = this;
                $.ajax({
                    type:"get",
                    url:"/getAllConfig",
                    success:function (result) {
                        self.configList = result;
                    }
                });
            },
            editConfig(id){
                this.editStep1Visible = true;
                this.editStep2Visible = false;
                var self = this;
                $.ajax({
                    type:"get",
                    url:"/getProjectConfig",
                    data:{"id":id},
                    success:function(result){
                        self.editForm = result;
                    }
                });
            },
            confirmAndNext(){
                var self = this;
                this.editStep1Visible = false;
                this.editStep2Visible = true;
                this.editSelectedList = [];
                var data = Qs.stringify(self.editForm);
                $.ajax({
                    type:"get",
                    url:"/updateProjectConfg",
                    data:data,
                    success:function(result){

                    }
                });
                this.initEditTables(this.editForm.id);
            },
            build(){
                var self = this;
                var tableNames = new Array();
                var entityNames = new Array();
                for(var i=0;i<self.newSelectedList.length;i++){
                    if(self.newSelectedList[i]){
                        var table = self.tables[i];
                        tableNames.push(table.tableName);
                        entityNames.push(table.entityName);
                    }
                }
                var tableList = tableNames.join(",");
                var entityList = entityNames.join(",");
                $.ajax({
                    type:"post",
                    url:"/build",
                    data:{"tableList":tableList,"entityList":entityList,"id":self.step1Form.id},
                    success:function(result){
                        self.showResult(result);
                    }
                });
            },
            editBuild(){
                var self = this;
                var tableNames = new Array();
                var entityNames = new Array();
                for(var i=0;i<self.editSelectedList.length;i++){
                    if(self.editSelectedList[i]){
                        var table = self.tables[i];
                        tableNames.push(table.tableName);
                        entityNames.push(table.entityName);
                    }
                }
                var tableList = tableNames.join(",");
                var entityList = entityNames.join(",");
                $.ajax({
                    type:"post",
                    url:"/build",
                    data:{"tableList":tableList,"entityList":entityList,"id":self.editForm.id},
                    success:function(result){
                        self.showResult(result);
                    }
                });
            },
            selectedAll(){
                this.setCheckboxesStatus(this.isSelectAll);
            },
            editSelectedAll(){
                this.setCheckboxesStatus(this.isEditSelectAll);
            },
            closeResult(){
                this.isShowResult = false;
            },
            showResult(result){
                var self = this;
                if(result.code == 0){
                    self.resultClass = " label label-success";
                    self.buildResult = "生成文件成功";
                    self.waringList = result.data;
                }else{
                    self.resultClass = "label label-danger";
                    self.buildResult = "生成出现问题："+result.message;
                }
            }
        },
        beforeMount() {
            this.initConfigList();
        }
    });
</script>
</html>