<div id="badge-editor-flash">
    <p>Adobe Flash player is required.</p>
    <p><a href="http://www.adobe.com/go/getflashplayer">Click here to install Adobe Flash player</a>.</p>
</div>
<script type="text/javascript" language="JavaScript">
    var swfobj = new SWFObject("{{ site.staticContentPath }}/flash/BadgeEditor.swf", "badgeEditor", "280", "366", "8");
    swfobj.addParam("base", "{{ site.staticContentPath }}/flash/");
    swfobj.addParam("bgcolor", "#FFFFFF");
    swfobj.addVariable("post_url", "{{ site.sitePath }}/groups/actions/update_group_badge?");
    swfobj.addVariable("__app_key", "1");
    swfobj.addVariable("groupId", "{{ group.getId() }}");
    swfobj.addVariable("badge_data", "{{ group.getBadge() }}");
    swfobj.addVariable("localization_url", "{{ site.staticContentPath }}/xml/badge_editor.xml");
    swfobj.addVariable("xml_url", "{{ site.staticContentPath }}/xml/badge_data.xml");
    swfobj.addParam("allowScriptAccess", "always");
    swfobj.write("badge-editor-flash");
</script>