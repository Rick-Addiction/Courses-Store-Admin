<template>
  <div>
    <el-tabs v-model="activeName2" tab-position="left" style="height: 600px;" @tab-click="handleClick">
      <el-tab-pane
        v-for="(item) in coursesRelatedTabs"
        :key="item.name"
        :label="item.title"
        :name="item.name"
      >
        <component :is="item.content" :current-customer="currentCustomer" :update="item.updateContent" @updateComplete="restoreTabs" />
      </el-tab-pane>
    </el-tabs></div>
</template>

<script>
import acquiredCoursesTab from './acquiredCourses.vue'
import desiredCoursesTab from './desiredCourses.vue'

export default {
  components: {
    'acquired-courses-tab': acquiredCoursesTab,
    'desired-courses-tab': desiredCoursesTab
  },
  props: {
    currentCustomer: {
      type: Object,
      default: null
    }
  },
  data() {
    return {
      activeName2: 'Acquired',
      coursesRelatedTabs: [
        {
          title: 'Acquired',
          name: 'Acquired',
          content: acquiredCoursesTab,
          updateContent: false
        },
        {
          title: 'Desired',
          name: 'Desired',
          content: desiredCoursesTab,
          updateContent: false
        }
      ]
    }
  },
  mounted() {
    this.coursesRelatedTabs[0].updateContent = true
  },
  methods: {
    handleClick(tab) {
      this.coursesRelatedTabs[tab.index].updateContent = true
      console.log('handleClick')
      console.log(this.coursesRelatedTabs)
    },
    restoreTabs() {
      this.coursesRelatedTabs.forEach((value) => {
        value.updateContent = false
      })
      console.log('restoreTabs')
      console.log(this.coursesRelatedTabs)
    }
  }
}

</script>

