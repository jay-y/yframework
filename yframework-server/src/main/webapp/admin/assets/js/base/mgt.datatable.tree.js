(function ($, d) {
    if (null == d || undefined == d) {
        alert("mgt can not be empty.");
        return;
    }
    d.tree = {
        onInit: function (t, settings, data) {
            if (null == settings || undefined == settings) {
                alert("Settings can not be empty.");
                return;
            }
            if (!(t instanceof jQuery)) {
                alert("Table non - jQuery objects.");
                return;
            }
            var config = d.getConfig(t, settings);
            var parentIds = [];
            var menus = {};
            if(data)
            {
                config.aoData = data;
                config.bServerSide = false;
                config.fnServerData = function (sSource, aoData, fnCallback, oSettings) {

                }
            }
            config.bSort = false;
            config.bPaginate = false;
            config.aoColumnDefs = [
                {
                    "aTargets": [1],
                    "mRender": function (cell, b, row, d) {
                        var parentId = row.parentId;
                        var id = row.id;
                        if (null != parentId && undefined != parentId && !parentIds.includes(parentId)) {
                            parentIds.push(parentId);
                        }
                        menus[id] = (menus[parentId] ? (menus[parentId] + '-') : '') + (parentId ? parentId : '');
                        var res = '<i data-tree-id="' + id + '" data-tree-parent-id="' + menus[id] + '"></i>';
                        return res + cell;
                    }
                }
            ];
            var dt = t.DataTable(config);
            dt.on('draw', function (event) {
                var r = $(".treetable > tbody > tr");
                if (r && !td) {
                    parentIds.forEach(function (o) {
                        $('.treetable tr > td > i[data-tree-id="' + o + '"]').addClass('fa fa-minus-square');
                    });
                    for (var k in menus) {
                        var v = menus[k];
                        var length = v ? v.split('-').length : 0;
                        //TODO 优化样式
                        if (length > 0) {
                            $('td:has(i[data-tree-parent-id="' + v + '"])').css('paddingLeft', length * 15 + 'px');
                        }
                    }
                    var td = $(".treetable tr > td:has(i[data-tree-id])");
                    td.on("click", function (a) {
                        var i = $(this).find('i');
                        var parentId = i.data('treeId');
                        var b = $('td:has(i[data-tree-parent-id*="' + parentId + '"])').parent('tr');
                        if (i.is(".fa")) {
                            if (b.is(":visible")) {
                                b.find(' > td > i[data-tree-id].fa').addClass('fa-plus-square').removeClass("fa-minus-square");
                                b.hide("fast");
                                $(this).find(" > i").addClass("fa-plus-square").removeClass("fa-minus-square");
                            }
                            else {
                                b.find(' > td > i[data-tree-id].fa').addClass('fa-minus-square').removeClass("fa-plus-square");
                                b.show("fast");
                                $(this).find(" > i").addClass("fa-minus-square").removeClass("fa-plus-square");
                                a.stopPropagation();
                            }
                        }
                    });
                }
            });
            d.instance = dt;
            return dt;
        }
    };
    return d.tree;
})($ || jQuery, mgt ? mgt.datatable : null);