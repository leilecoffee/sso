<template>
  <div class="view-page">
		<h-section></h-section>
    <left-slide></left-slide>
    <main-content>
      <router-view></router-view>
    </main-content>
  </div>
</template>
<script type="text/javascript">
  import {hSection,mainContent, leftSlide} from 'components'

  export default{
    name: 'page',
    components: {
			hSection,
      mainContent,
      leftSlide
    }
  }
</script>
