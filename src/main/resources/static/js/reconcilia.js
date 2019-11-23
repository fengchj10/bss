/**
 * 校外客户信息列表
 */

//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

// Vue实例
var reconciliaVue = new Vue({
    el: '#reconcilia',
    data() {
        return {
            loginName:'',
            //element-ui的table需要的参数必须是Array类型的
            reconciliaData: [{
                id: '',
                title: '',
                price: '',
                image: '',
                brand: ''
            }],

            //添加dialog
            showSave: false,
            //编辑dialog
            showEditor: false,

            //条件查询单独封装的对象
            searchEntity: {
                checkDate: "",
                otherInfo: ""
            },

            //分页选项
            pageConf: {
                //设置一些初始值(会被覆盖)
                pageCode: 1, //当前页
                pageSize: 10, //每页显示的记录数
                totalPage: 12, //总记录数
                pageOption: [10, 20, 30], //分页选项
            },

            loading: {},

            activeIndex: '3', //默认激活


            expireTimeOption: {
                //当前日期之前的时间禁用
                //disabledDate 文档上：设置禁用状态，参数为当前日期，要求返回 Boolean
                disabledDate(date) {
                    return date.getTime() > Date.now();
                }
            },
            apiServerPath:"",


        }
    },
    methods: {
        /**
         * loading加载动画
         */
        loadings() {
            this.loading = this.$loading({
                lock: true,
                text: '拼命加载中',
                spinner: 'el-icon-loading',
            });
            setTimeout(() => {
                this.loading.close();
            }, 2000);
        },
        getName(){
            this.loginName = sessionStorage.getItem("name");

        },
        //初始化下载路径
        guessApiServerPath() {
            if (this.apiServerPath === "") {
                // apiServerPath = window.location.protocol + "//" + window.location.host + "/bss";
                this.apiServerPath = window.location.protocol + "//" + window.location.host;
            }
            sessionStorage.setItem("serverPath", this.apiServerPath);
        },
        //根据JSON对象构造querystring
        createQueryString(params) {
            var str = "?",
                param;
            for (param in params) {
                if (params.hasOwnProperty(param)) {
                    str = str + param + "=" + encodeURIComponent(params[param]) + "&";
                }
            }
            return str.slice(0, -1);
        },
        formatterActType(val) {

            if (val.actType == 'A') {
                return '开通'
            } else if (val.actType == 'R') {
                return '退订'
            } else if (val.actType == 'X') {
                return ' 不变'
            }else{
                return ' --'
            }
        },
        formatterInstitutionCode(val) {
            if (val.institutionCode == '0000') {
                return '外勤管家'
            } else if (val.institutionCode == '0001') {
                return '初定外平台'
            } else if (val.institutionCode == '0002') {
                return ' 音乐公司'
            }else if (val.institutionCode == '0003') {
                return ' 产创云（COP平台）'
            }else if (val.institutionCode == '0004') {
                return ' MDM平台'
            }else{
                return ' --'
            }
        },

        /**
         * Public method
         */
        //刷新列表
        reloadList() {
            console.log("totalPage:" + this.pageConf.totalPage + ",pageSize:" + this.pageConf.pageSize + ",:pageCode:" + this.pageConf.pageCode);
            this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        },
        //条件查询
        search(pageCode, pageSize) {
            this.loadings();
            this.$http.post('/bss/reconcilia/findByConPage?pageSize=' + pageSize + '&pageCode=' + pageCode, this.searchEntity).then(result => {
                console.log(result);
                this.reconciliaData = result.body.rows;
                this.pageConf.totalPage = result.body.total;
                this.loading.close(); //数据更新成功就手动关闭动画
            });

        },
        exportExcel(){
            var serverPath = sessionStorage.getItem("serverPath"),
                fileUrl = serverPath + "/bss/reconcilia/exportDailyReport";
            fileUrl += this.createQueryString({
                // format: "xls",
                checkDate: this.searchEntity.checkDate,
                otherInfo: this.searchEntity.otherInfo
                // access_token: sessionStorage.getItem("token")
            });
            window.open(fileUrl);
        },


        //pageSize改变时触发的函数
        handleSizeChange(val) {
            this.search(this.pageConf.pageCode, val);
        },
        //当前页改变时触发的函数
        handleCurrentChange(val) {
            this.pageConf.pageCode = val; //为了保证刷新列表后页面还是在当前页，而不是跳转到第一页
            this.search(val, this.pageConf.pageSize);
        }


    },


    // 生命周期函数
    created() {
        // this.findAll();
        this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        this.loadings(); //加载动画
        this.getName();
        this.guessApiServerPath();//初始化请求方法
    },

});
