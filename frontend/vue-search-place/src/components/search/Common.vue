<template>
<body class="d-flex flex-column h-100">
  <header>
    <!-- Fixed navbar -->
    <b-navbar toggleable="md" type="dark" variant="info">
      <b-navbar-brand to="/search">장소 검색 서비스</b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <router-link to="/search" class="nav-link">장소 검색</router-link>
          <router-link to="/popular" class="nav-link">인기 검색</router-link>
          <router-link to="/history" class="nav-link">검색 내역</router-link>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </header>

  <!-- Begin page content -->
  <main role="main" class="flex-shrink-0">
    <div class="container">
      <slot></slot>
    </div>
  </main>

  <footer class="footer mt-auto py-3">
    <div class="container">
      <span class="text-muted">Place sticky footer content here.</span>
    </div>
  </footer>
</body>
</template>

<script>
export default {
  name: 'Common',
  data () {
    return {}
  }
}
</script>

<style scoped>
#navbarCollapse{ width: 100%; }
.router-link-active{ color: #ffffff!important; }
main > .container { padding: 30px 15px 0; }

.footer {
  background-color: #f5f5f5;
}

.footer > .container {
  padding-right: 15px;
  padding-left: 15px;
}

code {
  font-size: 80%;
}
</style>
