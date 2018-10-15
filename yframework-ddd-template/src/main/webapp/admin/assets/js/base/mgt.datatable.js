/**
 * Created by creator on 2018/2/26.
 */
mgt.datatable = (function ($, y) {
    const _CLASS_CUSTOM_CONDITION_MORE = 'custom-condition-more';
    return {
        instance: {},
        onInit: function (t, settings) {
            if (y.validator.isEmpty(settings)) {
                alert("Settings can not be empty.");
                return;
            }
            if (!(t instanceof jQuery)) {
                alert("Table non - jQuery objects.");
                return;
            }
            $('.' + _CLASS_CUSTOM_CONDITION_MORE).hide();
            this.instance = t.DataTable(this.getConfig(t, settings));
            return this.instance;
        },
        getConfig: function (t, settings) {
            if (y.validator.isEmpty(settings)) {
                alert("Settings can not be empty.");
                return;
            }
            var responsiveHelper_datatable_fixed_column = undefined;
            var breakpointDefinition = {
                tablet: 1024,
                phone: 480
            };
            var custToolbarTr = '';
            var conditionTr = '';
            var headTr = '';
            if (false != settings && settings.columns) {
                // 拼装表头信息
                custToolbarTr += '<tr class="cust-toolbar"> <td colspan="' + settings.columns.length + '"> <button onclick="mgt.datatable.toggleCondition();" class="btn btn-sm bg-color-red txt-color-white"><i class="fa fa-lg fa-filter" title="更多条件"></i>更多条件 </button>';
                conditionTr += "<tr class='" + _CLASS_CUSTOM_CONDITION_MORE + " cust-toolbar' style='display: none;'> <td colspan='" + settings.columns.length + "'>";
                headTr += "<tr>";
                for (var index in settings.columns) {
                    var col = settings.columns[index];
                    var hasData = y.validator.isNotEmpty((col.mData || col.data));
                    var sName = y.validator.isEmpty(col.sName) ? col.mData : col.sName;
                    var sType = y.validator.isEmpty(col.sType) ? "text" : col.sType;
                    var sTitle = y.validator.isEmpty(col.sTitle) ? (hasData ? col.mData : "") : col.sTitle;
                    var cTitle = y.validator.isEmpty(col.cTitle) ? sTitle : col.cTitle;
                    var searchable = false != col.searchable;
                    var sDefault = col.sDefault && false != col.sDefault;
                    if (sDefault) {
                        custToolbarTr += '<input name="' + sName + '" placeholder="请输入' + cTitle + '" class="form-control custom-condition"/>';
                    }
                    conditionTr += (!sDefault && hasData && searchable ? "<input name='" + sName + "' type='" + sType + "' class='form-control custom-condition" + (col.cClass ? " " + col.cClass : "") + "' placeholder='" + cTitle + "'/>" : "");
                    headTr += "<th>" + sTitle + "</th>"
                }
                custToolbarTr += '<button onclick="mgt.datatable.instance.draw();" class="btn btn-sm bg-color-blue txt-color-white"><i class="fa fa-lg fa-search" title="搜索"></i>搜索 </button> </td> </tr>';
                conditionTr += "</td></tr>";
                headTr += "</tr>";
                if (false == settings.hasToolbar) {
                    custToolbarTr = '';
                }
                if (false == settings.hasCondition) {
                    conditionTr = '';
                }
                if (false == settings.hasHead) {
                    headTr = '';
                }
                var thead = t.find('thead');
                thead && thead.length > 0 ? thead.append(custToolbarTr).append(conditionTr).append(headTr) : t[0].innerHTML = "<thead>" + custToolbarTr + conditionTr + headTr + "</thead>";
            }
            return {
                "oLanguage": { // 汉化
                    "sSearch": '<span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>',
                    "sLengthMenu": "每页显示 _MENU_ 条记录",
                    "sZeroRecords": "没有检索到数据",
                    "sInfo": "显示 _START_ 至 _END_ 条 &nbsp;&nbsp;共 _TOTAL_ 条",
                    "sInfoFiltered": "(筛选自 _MAX_ 条数据)",
                    "sInfoEmpty": "显示 0 至 0 条 &nbsp;&nbsp;共 0 条",
                    // "sProcessing": "正在加载数据...",
                    "sProcessing": "<img src='assets/image/ajax-loader.gif' />", //这里是给服务器发请求后到等待时间显示的 加载gif
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "上一页",
                        "sNext": "下一页",
                        "sLast": "尾页"
                    }
                },
                "sPaginationType": "full_numbers", // 分页，一共两种样式 ，默认是两个按钮那种
                "bProcessing": true,
                "bDeferRender": true, //当处理大数据时，延迟渲染数据，有效提高Datatables处理能力
                "bPaginate": true, //分页按钮 默认为true
                "stripeClasses": ["odd", "even"], //为奇偶行加上样式，兼容不支持CSS伪类的场合
                "aoColumns": null != settings.columns ? settings.columns : [],
                "aoColumnDefs": null != settings.formatter ? settings.formatter : [],
                "bServerSide": true,
                "sAjaxSource": settings.url,
                "fnServerData": function (sSource, aoData, fnCallback, oSettings) {
                    //数组转键值对
                    var params = {};
                    aoData.forEach(function (i) {
                        if (params[i.name] !== undefined) {
                            if (!params[i.name].push) {
                                params[i.name] = [params[i.name]];
                            }

                            if (typeof i.value === 'string') {
                                params[i.name].push(i.value || '');
                            }
                            else {
                                params[i.name].push(i.value);
                            }
                        }
                        else {
                            if (typeof i.value === 'string') {
                                params[i.name] = i.value || '';
                            }
                            else {
                                params[i.name] = i.value;
                            }
                        }
                    });
                    var colArray = params["sColumns"].split(",");
                    var sortIndex = params["iSortCol_0"];
                    var sortMode = params["sSortDir_0"];
                    var col = colArray[sortIndex];
                    var sort = "id,desc";

                    //处理只有一个栏位参与排序
                    if (undefined != col && null != col && "" != col) {
                        sort = (col || 'id') + "," + (sortMode || 'desc');
                    }

                    //分页
                    var size = params["iDisplayLength"];
                    var page = params["iDisplayStart"] / params["iDisplayLength"];

                    //表单栏位
                    var conditions = $(document.getElementsByClassName("custom-condition"));
                    var query = {};
                    for (var i = 0; i < conditions.length; i++) {
                        var condition = conditions[i];
                        if (y.validator.isNotEmpty(condition.name) && y.validator.isNotEmpty(condition.value)) {
                            query[condition.name] = condition.value;
                        }
                    }
                    if (oSettings.oFeatures && oSettings.oFeatures.bPaginate) {
                        mgt.service.query(
                            sSource,
                            {
                                query: query,
                                page: page,
                                size: size,
                                sort: sort
                            },
                            function (res) {
                                var data = res;
                                if (data instanceof Array) {
                                    res.iTotalRecords = data.length;
                                    res.iTotalDisplayRecords = data.length;
                                } else {
                                    res.iTotalRecords = data.totalElements;
                                    res.iTotalDisplayRecords = data.totalElements;
                                    res.data = y.validator.isNotEmpty(data.content) ? data.content : [];
                                }
                                fnCallback(res);//服务器端返回的对象resp是要求的格式
                                settings.success && settings.success(res);
                            },
                            function (res) {
                                settings.error && settings.error(res);
                            }
                        );
                    } else {
                        mgt.service.get(
                            sSource,
                            query,
                            function (res) {
                                var data = {
                                    data: y.validator.isNotEmpty(res) ? res : [],
                                    length: res.length,
                                    iTotalRecords: res.length,
                                    iTotalDisplayRecords: res.length
                                };
                                fnCallback(data);//服务器端返回的对象resp是要求的格式
                                settings.success && settings.success(res);
                            },
                            function (res) {
                                settings.error && settings.error(res);
                            }
                        );
                    }
                },
                "sDom": "<'dt-toolbar'<'custom-toolbar col-sm-6 col-xs-12'><'col-sm-6 col-xs-12 hidden-xs'CT>r>" +
                "t" +
                "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
                "oTableTools": {
                    "aButtons": [
                        {
                            "sExtends": "print",
                            "sButtonText": "打印"
                        }
                    ],
                    "sSwfPath": "/admin/assets/js/lib/datatables/swf/copy_csv_xls_pdf.swf"
                },
                "preDrawCallback": function () {
                    // Initialize the responsive datatables helper once.
                    if (!responsiveHelper_datatable_fixed_column) {
                        responsiveHelper_datatable_fixed_column = new ResponsiveDatatablesHelper(t, breakpointDefinition);
                    }
                },
                "rowCallback": function (nRow) {
                    responsiveHelper_datatable_fixed_column.createExpandIcon(nRow);
                },
                "drawCallback": function (oSettings) {
                    responsiveHelper_datatable_fixed_column.respond();
                }
            };
        },
        toggleCondition: function (cb) {
            var c = $('.' + _CLASS_CUSTOM_CONDITION_MORE);
            var v = c.is(":visible");
            c.is(":visible") ? c.hide('fast') : c.show('fast');
            cb && cb(v);
        }
    }
})($ || jQuery, yutil);