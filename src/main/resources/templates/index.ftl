<!DOCTYPE html>
<html lang="zh">
<#include "common/head.ftl">
  
<body>
<div class="lyear-layout-web">
  <div class="lyear-layout-container">
    <!--左侧导航-->
    <#include "common/aside.ftl">
    <!--End 左侧导航-->

    
    <!--页面主要内容-->
    <main class="lyear-layout-content">
      
      <div class="container-fluid">
        
        <div class="row">
          <div class="col-sm-2 col-lg-2">
            <div class="card bg-danger">
              <div class="card-body clearfix">
                <div class="pull-right">
                  <p class="h6 text-white m-t-0">全服玩家总数</p>
                  <p class="h3 text-white m-b-0">${accountNum}</p>
                </div>
                <div class="pull-left"> <span class="img-avatar img-avatar-48 bg-translucent"><i class="mdi mdi-account fa-1-5x"></i></span> </div>
              </div>
            </div>
          </div>
          <div class="col-sm-6 col-lg-3">
            <div class="card">
              <div class="card-header bg-primary">
                <h4>用户名id</h4>
                <ul class="card-actions">
                  <li>
                    <button type="button"><i class="mdi mdi-more"></i></button>
                  </li>
                </ul>
              </div>
              <div class="card-body">
                <#if status != 0>
                  <p>查不到...</p>
                <#else >
                  <p>${account.getUID()}</p>
                </#if>
              </div>
            </div>
          </div>
          <!-- .col-sm-6 -->
          <div class="col-sm-6 col-lg-3">
            <div class="card">
              <div class="card-header bg-success">
                <h4>qq</h4>
                <ul class="card-actions">
                  <li>
                    <button type="button"><i class="mdi mdi-more"></i></button>
                  </li>
                </ul>
              </div>
              <div class="card-body">
                <#if status != 0>
                  <p>查不到...</p>
                <#else >
                  <p>${account.getQq()}</p>
                </#if>
              </div>
            </div>
          </div>
          <!-- .col-sm-6 -->
        </div>
        <div class="row">
          <div class="col-md-2">
            <div class="card">
              <div class="card-header"><h4>查询账号信息</h4></div>
              <div class="card-body">
                <form action="/account/info" method="post">
                  <div class="form-group">
                    <input class="form-control" type="text" name="accountname" placeholder="请输入查询的账号..">
                  </div>
                  <div class="form-group">
                    <button class="btn btn-primary" type="submit">查询</button>
                  </div>
                </form>
              </div>
            </div>
          </div>

          <div class="col-sm-6 col-lg-3">
            <div class="card">
              <div class="card-header bg-info">
                <h4>D点余额</h4>
                <ul class="card-actions">
                  <li>
                    <button type="button"><i class="mdi mdi-more"></i></button>
                  </li>
                </ul>
              </div>
              <div class="card-body">
                <#if status != 0>
                  <p>查不到...</p>
                <#else >
                  <p>${account.getCera()}</p>
                </#if>
              </div>
            </div>
          </div>
          <!-- .col-sm-6 -->

          <div class="col-sm-6 col-lg-3">
            <div class="card">
              <div class="card-header bg-warning">
                <h4>D币余额</h4>
                <ul class="card-actions">
                  <li>
                    <button type="button"><i class="mdi mdi-more"></i></button>
                  </li>
                </ul>
              </div>
              <div class="card-body">
                <#if status != 0>
                  <p>查不到...</p>
                <#else >
                  <p>${account.getCera_point()}</p>
                </#if>
              </div>
            </div>
          </div>
          <!-- .col-sm-6 -->
        </div>

        <div class="row">
          
          <div class="col-lg-12">
            <div class="card">
              <div class="card-header">
                <h4>账号角色信息</h4>
              </div>
              <div class="card-body">
                <div class="table-responsive">
                  <table class="table table-hover">
                    <thead>
                      <tr>
                        <th style="width: 10%">角色id</th>
                        <th style="width: 15%">角色名</th>
                        <th style="width: 10%">职业</th>
                        <th>等级(70满级)</th>
                        <th>角色创建时间</th>
                      </tr>
                    </thead>
                    <tbody>
                    <#if !roles??>
                      <tr>
                        <td>查下你的角色吧</td>
                        <td>..</td>
                        <td><span class="label label-warning">..</span></td>
                        <td>
                          <div class="progress progress-striped progress-sm">
                            <div class="progress-bar progress-bar-warning" style="width: 0;">..</div>
                          </div>
                        </td>
                      </tr>
                    <#else >
                      <#list roles as role>
                        <tr>
                          <td>${role.charac_no}</td>
                          <td>${role.charac_name}</td>
                          <td><span class="label label-warning">${role.job}</span></td>
                          <td>
                            <div class="progress progress-striped progress-sm">
                              <div class="progress-bar progress-bar-warning" style="width: ${role.lev / 70 * 100}%;">${role.lev}</div>
                            </div>
                          </td>
                          <td>${role.getCreate_time()}</td>
                        </tr>
                      </#list>
                    </#if>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>

        </div>
        
      </div>
      
    </main>
    <!--End 页面主要内容-->
  </div>
</div>

<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="/js/main.min.js"></script>

<!--图表插件-->
</body>
</html>