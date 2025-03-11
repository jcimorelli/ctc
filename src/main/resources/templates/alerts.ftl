<#-- alerts.ftl -->
<#if errorMessage??>
    <div class="alert alert-error">
        ${errorMessage}
    </div>
</#if>

<#if warningMessage??>
    <div class="alert alert-warning">
        ${warningMessage}
    </div>
</#if>

<#if confirmationMessage??>
    <div class="alert alert-confirmation">
        ${confirmationMessage}
    </div>
</#if>