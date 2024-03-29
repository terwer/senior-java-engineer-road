//这个函数在整个wps加载项中是第一个执行的
function OnAddinLoad(ribbonUI) {
    if (typeof (wps.ribbonUI) != "object") {
        wps.ribbonUI = ribbonUI
    }

    if (typeof (wps.Enum) != "object") { // 如果没有内置枚举值
        wps.Enum = WPS_Enum
    }

    wps.PluginStorage.setItem("EnableFlag", false) //往PluginStorage中设置一个标记，用于控制两个按钮的置灰
    wps.PluginStorage.setItem("ApiEventFlag", false) //往PluginStorage中设置一个标记，用于控制ApiEvent的按钮label
    return true
}

var WebNotifycount = 0;

function OnAction(control) {
    const eleId = control.Id
    switch (eleId) {
        case "btnShowMsg": {
            const doc = wps.WpsApplication().ActiveDocument
            if (!doc) {
                alert("当前没有打开任何文档")
                return
            }

            // =======================
            var wdStyleBodyText = -67//正文文本。
            var wdStyleHeading1 = -2//标题 1。
            var wdStyleHeading2 = -3//标题 2。
            var wdStyleHeading3 = -4//标题 3。
            var wdStyleHeading4 = -5//标题 4。
            var wdStyleHeading5 = -6//标题 5。
            var wdStyleHeading6 = -7//标题 6。
            var wdStyleHeading7 = -8//标题 7。
            var wdStyleHeading8 = -9//标题 8。
            var wdStyleHeading9 = -10//标题 9。

            // 插入文本
            var myRange = doc.Range(doc.Content.End - 1, doc.Content.End - 1);
            var start = doc.Content.End - 1;
            myRange.Style = wdStyleHeading2;
            // myRange.text = "标题2";
            myRange.InsertAfter("test");
            // myRange.InsertBefore("test");
            var end = doc.Content.End - 1;
            var newRange = doc.Range(start, end);
            // alert(newRange.Text)

            doc.Indexes.MarkEntry(newRange, newRange.Text);

            /*本示例在活动文档的末尾添加索引。*/
            // var myRange = doc.Range(doc.Content.End - 1, doc.Content.End - 1);
            var myRange = doc.Range(0, 0);
            doc.Indexes.Add(myRange, false, null, null, 1);

            doc.Indexes.Format = 4;
            console.log(doc.Indexes);
            // =========================
            alert(doc.Name)
        }
            break;
        case "btnIsEnbable": {
            let bFlag = wps.PluginStorage.getItem("EnableFlag")
            wps.PluginStorage.setItem("EnableFlag", !bFlag)

            //通知wps刷新以下几个按饰的状态
            wps.ribbonUI.InvalidateControl("btnIsEnbable")
            wps.ribbonUI.InvalidateControl("btnShowDialog")
            wps.ribbonUI.InvalidateControl("btnShowTaskPane")
            //wps.ribbonUI.Invalidate(); 这行代码打开则是刷新所有的按钮状态
            break
        }
        case "btnShowDialog":
            wps.ShowDialog(GetUrlPath() + "/ui/dialog.html", "这是一个对话框网页", 400 * window.devicePixelRatio, 400 * window.devicePixelRatio, false)
            break
        case "btnShowTaskPane": {
            let tsId = wps.PluginStorage.getItem("taskpane_id")
            if (!tsId) {
                let tskpane = wps.CreateTaskPane(GetUrlPath() + "/ui/taskpane.html")
                let id = tskpane.ID
                wps.PluginStorage.setItem("taskpane_id", id)
                tskpane.Visible = true
            } else {
                let tskpane = wps.GetTaskPane(tsId)
                tskpane.Visible = !tskpane.Visible
            }
        }
            break
        case "btnApiEvent": {
            let bFlag = wps.PluginStorage.getItem("ApiEventFlag")
            let bRegister = bFlag ? false : true
            wps.PluginStorage.setItem("ApiEventFlag", bRegister)
            if (bRegister) {
                wps.ApiEvent.AddApiEventListener('DocumentNew', OnNewDocumentApiEvent)
            } else {
                wps.ApiEvent.RemoveApiEventListener('DocumentNew', OnNewDocumentApiEvent)
            }

            wps.ribbonUI.InvalidateControl("btnApiEvent")
        }
            break
        case "btnWebNotify": {
            let currentTime = new Date()
            let timeStr = currentTime.getHours() + ':' + currentTime.getMinutes() + ":" + currentTime.getSeconds()
            wps.OAAssist.WebNotify("这行内容由wps加载项主动送达给业务系统，可以任意自定义, 比如时间值:" + timeStr + "，次数：" + (++WebNotifycount), true)
        }
            break
        default:
            break
    }
    return true
}

function GetImage(control) {
    const eleId = control.Id
    switch (eleId) {
        case "btnShowMsg":
            return "images/1.svg"
        case "btnShowDialog":
            return "images/2.svg"
        case "btnShowTaskPane":
            return "images/3.svg"
        default:
            ;
    }
    return "images/newFromTemp.svg"
}

function OnGetEnabled(control) {
    const eleId = control.Id
    switch (eleId) {
        case "btnShowMsg":
            return true
            break
        case "btnShowDialog": {
            let bFlag = wps.PluginStorage.getItem("EnableFlag")
            return bFlag
            break
        }
        case "btnShowTaskPane": {
            let bFlag = wps.PluginStorage.getItem("EnableFlag")
            return bFlag
            break
        }
        default:
            break
    }
    return true
}

function OnGetVisible(control) {
    return true
}

function OnGetLabel(control) {
    const eleId = control.Id
    switch (eleId) {
        case "btnIsEnbable": {
            let bFlag = wps.PluginStorage.getItem("EnableFlag")
            return bFlag ? "按钮Disable" : "按钮Enable"
            break
        }
        case "btnApiEvent": {
            let bFlag = wps.PluginStorage.getItem("ApiEventFlag")
            return bFlag ? "清除新建文件事件" : "注册新建文件事件"
            break
        }
    }
    return ""
}

function OnNewDocumentApiEvent(doc) {
    alert("新建文件事件响应，取文件名: " + doc.Name)
}
