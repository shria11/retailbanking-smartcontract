<template>
  <CHeader position="sticky" class="mb-4">
    <CContainer fluid>
      <CHeaderToggler class="ps-1" @click="$store.commit('toggleSidebar')">
        <CIcon icon="cil-menu" size="lg" />
      </CHeaderToggler>
      <CHeaderBrand class="mx-auto d-lg-none" to="/">
        <CIcon :icon="logo" height="48" alt="Logo" />
      </CHeaderBrand>
      <CHeaderNav class="d-none d-md-flex me-auto">
        <CNavItem>
          <CNavLink href="/dashboard"> Dashboard </CNavLink>
        </CNavItem>
      </CHeaderNav>
    </CContainer>
    <CHeaderDivider />
    <CContainer fluid>
      <AppBreadcrumb />
    </CContainer>
  </CHeader>
</template>

<script>
import AppBreadcrumb from './AppBreadcrumb'
export default {
  name: 'AppHeader',
  components: {
    AppBreadcrumb,
  },
  setup() {
    return {    }
  },
}
</script>
