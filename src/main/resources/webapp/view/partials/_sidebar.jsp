<%@ taglib prefix="s" uri="/struts-tags" %>
<aside class="mdc-drawer mdc-drawer--dismissible mdc-drawer--open">
    <div class="mdc-drawer__header">
        <a href="<s:url namespace="/" action="index"/>" class="brand-logo">
            <img src="${pageContext.request.contextPath}/assets/images/logo.svg" alt="logo">
        </a>
    </div>
    <div class="mdc-drawer__content">
        <div class="user-info">
            <p class="name">Senbonzakura</p>
            <p class="email">dungpath1805040@fpt.edu.vn</p>
        </div>
        <div class="mdc-list-group">
            <nav class="mdc-list mdc-drawer-menu">
                <div class="mdc-list-item mdc-drawer-item">
                    <a class="mdc-drawer-link" href="<s:url namespace="/" action="index"/>">
                        <i class="material-icons mdc-list-item__start-detail
                        mdc-drawer-item-icon" aria-hidden="true">home</i> Dashboard
                    </a>
                </div>
                <div class="mdc-list-item mdc-drawer-item">
                    <a class="mdc-drawer-link" href="<s:url namespace="/book" action="index"/>">
                        <i class="material-icons mdc-list-item__start-detail
                        mdc-drawer-item-icon"
                           aria-hidden="true">book</i> Book
                    </a>
                </div>
                <div class="mdc-list-item mdc-drawer-item">
                    <a class="mdc-drawer-link" href="<s:url namespace="/shop" action="index"/>">
                        <i class="material-icons mdc-list-item__start-detail
                        mdc-drawer-item-icon"
                           aria-hidden="true">card_membership</i> Publisher
                    </a>
                </div>
                <div class="mdc-list-item mdc-drawer-item">
                    <a class="mdc-drawer-link" href="<s:url namespace="/author" action="index"/>">
                        <i class="material-icons mdc-list-item__start-detail
                        mdc-drawer-item-icon"
                           aria-hidden="true">person</i> Author
                    </a>
                </div>
                <div class="mdc-list-item mdc-drawer-item">
                    <a class="mdc-drawer-link" href="<s:url namespace="/category" action="index"/>">
                        <i class="material-icons mdc-list-item__start-detail
                        mdc-drawer-item-icon"
                           aria-hidden="true">label</i> Category
                    </a>
                </div>
            </nav>
        </div>
        <div class="profile-actions">
            <a href="javascript:">Settings</a>
            <span class="divider"></span>
            <a href="javascript:">Logout</a>
        </div>
        <div class="mdc-card premium-card">
            <div class="d-flex align-items-center">
                <div class="mdc-card icon-card box-shadow-0">
                    <i class="mdi mdi-shield-outline"></i>
                </div>
                <div>
                    <p class="mt-0 mb-1 ml-2 font-weight-bold tx-12">Material Dash</p>
                    <p class="mt-0 mb-0 ml-2 tx-10">Pro available</p>
                </div>
            </div>
            <p class="tx-8 mt-3 mb-1">More elements. More Pages.</p>
            <p class="tx-8 mb-3">Starting from $25.</p>
            <a href="https://www.bootstrapdash.com/book/material-design-admin-template/" target="_blank">
                <span class="mdc-button mdc-button--raised mdc-button--white">Upgrade to Pro</span>
            </a>
        </div>
    </div>
</aside>
