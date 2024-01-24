<template>
  <div>
    <CRow>
      <CCol :md="12">
        <CCard class="mb-4">
          <CCardHeader> Users </CCardHeader>
          <CCardBody>
            <CTable align="middle" class="mb-0 border" hover responsive>
              <CTableHead color="light">
                <CTableRow>
                  <CTableHeaderCell class="text-center">
                    Id
                  </CTableHeaderCell>
                  <CTableHeaderCell class="text-center">Username</CTableHeaderCell>
                  <CTableHeaderCell class="text-center"
                    >Address</CTableHeaderCell
                  >
                  <CTableHeaderCell class="text-center"
                    >Private Key</CTableHeaderCell
                  >
                  <CTableHeaderCell class="text-center">Public Key</CTableHeaderCell>
                </CTableRow>
              </CTableHead>
              <CTableBody>
                <CTableRow v-for="item in tableExample" :key="item.id">
                  <CTableDataCell class="text-center">{{ item.id }}</CTableDataCell>
                  <CTableDataCell class="text-center">
                    <div>{{ item.username }}</div>
                  </CTableDataCell>
                  <CTableDataCell class="text-center">
                    {{ item.address }}
                  </CTableDataCell>
                  <CTableDataCell class="text-center">
                    {{ item.privateKey }}
                  </CTableDataCell>
                  <CTableDataCell class="text-center">
                    {{ item.publicKey }}
                  </CTableDataCell>
                </CTableRow>
              </CTableBody>
            </CTable>
          </CCardBody>
        </CCard>
      </CCol>
    </CRow>
  </div>

</template>

<script>
export default {
  name: 'Dashboard',
  components: {
  },
  setup() {  },
  data() {

    return {
      tableExample:[],
    }
   
  },
  methods: {
    loadUsers(){ 
      this.$axios
      .get('http://localhost:8082/user/')
      .then((response) => {
          if (response.status == 200) {
            //console.log(response.data)
            this.tableExample = response.data 

         console.log(  this.tableExample)
          }
        })
        .catch((error) => {
          console.log(error)
        })}},
  created () {
  this.loadUsers()
  
   
}}
</script>
