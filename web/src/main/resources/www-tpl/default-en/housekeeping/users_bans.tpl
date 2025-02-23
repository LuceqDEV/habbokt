{% include "housekeeping/base/header.tpl" %}
<body>
{% set bansActive = " active" %}
{% include "housekeeping/base/navigation.tpl" %}
<h1 class="mt-4">View and manage bans</h1>
{% include "housekeeping/base/alert.tpl" %}
<p>Manage all currently active bans on the hotel</p>
<div class="table-responsive">
    <form method="post">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Type</th>
                <th>Value</th>
                <th>Message</th>
                <th>Banned Util</th>
                <th>Banned At</th>
                <th>Banned By</th>
            </tr>
            </thead>
            <tbody>
            {% for ban in bans %}
            <tr>
                <td>
                    {% if (ban.getBanType().name() == 'MACHINE_ID') %}
                    Machine
                    {% endif %}
                    {% if (ban.getBanType().name() == 'USER_ID') %}
                    User
                    {% endif %}
                </td>
                <td>
                    {% if (ban.getBanType().name() == 'MACHINE_ID') %}
                    {% set bannedName = ban.getName() %}
                    {{ ban.getValue() }}
                    {% if bannedName != "" %}
                    &nbsp;-&nbsp;{{ bannedName }}
                    {% endif %}
                    {% endif %}
                    {% if (ban.getBanType().name() == 'USER_ID') %}
                    {% set bannedName = ban.getName() %}
                    {% if bannedName != "" %}
                    {{ bannedName }}
                    {% endif %}
                    {% endif %}
                </td>
                <td>
                    {{ ban.getMessage() }}
                </td>
                <td>
                    {{ ban.getBannedUtil() }}
                </td>
                <td>
                    {{ ban.getBannedAt() }}
                </td>
                <td>
                    {{ ban.getBannedBy() }}
                </td>
            </tr>
            {% endfor %}
            </tbody>
        </table>
    </form>
</div>
</div></div>
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
<script src="https://blackrockdigital.github.io/startbootstrap-simple-sidebar/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script>
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>
</body></html>

{% include "housekeeping/base/footer.tpl" %}