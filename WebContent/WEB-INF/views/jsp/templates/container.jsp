
<link
	href="//arshaw.com/js/fullcalendar-1.5.3/fullcalendar/fullcalendar.css"
	rel="stylesheet" />
<link
	href="http://arshaw.com/js/fullcalendar-1.5.3/fullcalendar/fullcalendar.print.css"
	rel="stylesheet" />
<div class="container">



	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li class="active"><a href="#home" role="tab" data-toggle="tab">
				<icon class="glyphicon glyphicon-calendar"></icon> Calender
		</a></li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
		<div class="tab-pane fade active in" id="home">
			<div id='calendar'></div>
		</div>
	</div>
	<div class="tab-pane fade" id="discussion">
		<div class="panel panel-default">
			<div class="formcontainer">
				<!-- Row start -->
				<div class="row">
					<div class="col-md-12 col-sm-6 col-xs-12">

						<div class="panel-body"></div>

					</div>
				</div>
				<!-- Row end -->

			</div>
		</div>
	</div>
</div>
<script>
	/* Javascript */
	$(function() {
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();

		$('#calendar').fullCalendar({
			header : {
				left : 'prev,next today',
				center : 'title',
				right : 'month,agendaWeek,agendaDay'
			},
			editable : true
		});
	});
</script>
<style>
/* CSS */
#calendar {
	width: 100%;
	margin: 0 auto;
}
</style>

<script class="cssdesk"
	src="//ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"
	type="text/javascript"></script>
<script class="cssdesk"
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/jquery-ui.min.js"
	type="text/javascript"></script>
<script class="cssdesk"
	src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.1.0/js/bootstrap.min.js"
	type="text/javascript"></script>
<script class="cssdesk"
	src="//arshaw.com/js/fullcalendar-1.5.3/fullcalendar/fullcalendar.min.js"
	type="text/javascript"></script>