/**
 * Created by creator on 2018/3/8.
 */
mgt.tree = (function ($) {
    return {
        currentMenu: {},
        load: function (obj, items) {
            var root = document.createElement("ul");
            var root_li = document.createElement("li");
            var root_li_span = document.createElement('span');
            root_li_span.id = '1,根目录';
            root_li_span.innerHTML = '根目录';
            root_li.appendChild(root_li_span);
            root_li.appendChild(this.createBranch(items));
            root.appendChild(root_li);
            obj.appendChild(root);
            this.onInit();
        },
        checked: function (ids) {
            ids.forEach(function (id) {
                $(".tree > ul").find('#' + id + '_checkbox').prop('checked', true);
            });
        },
        onInit: function () {
            var r = $(".tree > ul");
            if (r && !treebranch) {
                var treebranch = $(".tree").find("li:has(ul)").addClass("parent_li").attr("role", "treeitem").find(" > span");
                r.attr("role", "tree").find("ul").attr("role", "group") && treebranch.on("click", function (a) {
                    var b = $(this).parent("li.parent_li").find(" > ul > li");
                    b.is(":visible") ? (b.hide("fast") && $(this).find(" > i").addClass("icon-plus-sign").removeClass("icon-minus-sign")) : (b.show("fast") && $(this).find(" > i").addClass("icon-minus-sign").removeClass("icon-plus-sign")) && a.stopPropagation()
                });
                this.onInitCheck(r);
            }
        },
        getChecked: function () {
            var ids = [];
            var r = $(".tree > ul").find("li > input[type='checkbox']");
            for (var i = 0; i < r.length; i++) {
                var item = r[i];
                if (item.checked) {
                    ids.push(item.value.replace('_checkbox', ''));
                }
            }
            ids.push(1);
            return ids;
        },
        createNode: function (item) {
            var i;
            var branch;
            var li = document.createElement('li');
            li.id = item.id;
            if (this.currentMenu) {
                var currentMenuId = this.currentMenu.id;
                if (item.id == currentMenuId) {
                    li.hidden = true;
                }
            }
            var checkbox = this.createCheckbox(item);
            var ix = (null != item.children && item.children.length > 0) ? '<i class="fa fa-lg icon-minus-sign"></i>' : '<i class="fa fa-lg"></i>';
            var span = document.createElement('span');
            span.id = item.id + ',' + item.name;
            span.innerHTML = ix + item.name;
            li.appendChild(checkbox);
            li.appendChild(span);
            if (item.children.length > 0) {
                branch = mgt.tree.createBranch(item.children, false);
                li.appendChild(branch);
            }
            return li;
        },
        createBranch: function (items) {
            var ul = document.createElement('ul');
            items.forEach(function (item) {
                ul.appendChild(mgt.tree.createNode(item));
            });
            return ul;
        },
        createCheckbox: function (item) {
            var checkbox = document.createElement('input');
            checkbox.checked = false;
            checkbox.style.marginRight = '2px';
            checkbox.id = item.id + '_checkbox';
            checkbox.value = item.id;
            checkbox.type = 'checkbox';
            if (null != item.children && item.children.length > 0) {
                checkbox.name = item.id + '_checkbox';
                checkbox.className = item.id + '_checkbox-parent';
            }
            else {
                checkbox.name = item.parentId + '_checkbox';
                checkbox.className = item.parentId + '_checkbox-child';
            }
            return checkbox;
        },
        onInitCheck: function (root) {
            root.find("li:has(ul) > input[class$='_checkbox-parent']").change(function (e) {
                var checked = e.target.checked;
                var name = e.target.name;
                var child = $('.' + name + '-child');
                if (checked) {
                    child.prop('checked', true);
                }
                else {
                    child.prop('checked', false);
                }
                mgt.tree.getChecked();
            });
            root.find("li > input[class$='_checkbox-child']").change(function (e) {
                var checked = e.target.checked;
                var name = e.target.name;
                var parent = $('.' + name + '-parent');
                var child = $('.' + name + '-child');
                var childChecked = $('.' + name + '-child:checked');
                if (checked) {
                    parent.prop('checked', true);
                }
                else if (childChecked.length == 0) {
                    parent.prop('checked', false);
                }
                mgt.tree.getChecked();
            });
        }
    };
})($ || jQuery);