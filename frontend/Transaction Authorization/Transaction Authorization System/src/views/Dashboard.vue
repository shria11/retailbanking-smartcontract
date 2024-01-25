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
                  <CTableHeaderCell class="text-center">Username</CTableHeaderCell>
                  <CTableHeaderCell class="text-center"
                    >Address</CTableHeaderCell
                  >
                </CTableRow>
              </CTableHead>
              <CTableBody>
                <CTableRow v-for="item in tableExample" :key="item.username">
                  <CTableDataCell class="text-center">
                    <div>{{ item.username }}</div>
                  </CTableDataCell>
                  <CTableDataCell class="text-center">
                    {{ item.address }}
                  </CTableDataCell>
                </CTableRow>
              </CTableBody>
            </CTable>
          </CCardBody>
        </CCard>

        <CCard class="mb-4">
          <CCardHeader> Transactions </CCardHeader>
          <CCardBody>
            <CTable align="middle" class="mb-0 border" hover responsive>
              <CTableHead color="light">
                <CTableRow>
                  <CTableHeaderCell class="text-center">Amount</CTableHeaderCell>
                  <CTableHeaderCell class="text-center"
                    >Timestamp</CTableHeaderCell>
                    <CTableHeaderCell class="text-center"
                    >Status</CTableHeaderCell>
                    <CTableHeaderCell class="text-center"
                    >Authorized By</CTableHeaderCell>
                </CTableRow>
              </CTableHead>
              <CTableBody>
                <CTableRow v-for="item in transactionTableExample" :key="item.timestamp">
                  <CTableDataCell class="text-center">
                    <div>{{ item.amount }}</div>
                  </CTableDataCell>
                  <CTableDataCell class="text-center">
                    {{ item.timestamp }}
                  </CTableDataCell>
                  <CTableDataCell class="text-center">
                    {{ item.status }}
                  </CTableDataCell>
                  <CTableDataCell class="text-center">
                    {{ item.user }}
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
      transactionTableExample:[],
    }
   
  },
  methods: {
    loadUsers(){ 
      this.$axios
      .get('http://localhost:8082/user/')
      .then((response) => {
          if (response.status == 200) {
            this.tableExample = response.data 
          }
        })
        .catch((error) => {
          console.log(error)
        })},

        loadTransactions(){ 
      this.$axios
      .get('http://localhost:8082/transaction/')
      .then((response) => {
          if (response.status == 200) {
            this.transactionTableExample = response.data 
          }
        })
        .catch((error) => {
          console.log(error)
        })}
      
      
      },
  created () {
  this.loadUsers()
  this.loadTransactions()
  
   
}}
</script>
