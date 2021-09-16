// For opening the note modal
function showNoteModal(noteId, noteTitle, noteDescription) {
	$('#note-id').val(noteId ? noteId : '');
	$('#note-title').val(noteTitle ? noteTitle : '');
	$('#note-description').val(noteDescription ? noteDescription : '');
	$('#noteModal').modal('show');
}

// For opening the credentials modal
function showCredentialModal(credentialId, url, username, password) {
	$('#credential-id').val(credentialId ? credentialId : '');
	$('#credential-url').val(url ? url : '');
	$('#credential-username').val(username ? username : '');
	$('#credential-password').val(password ? password : '');
	$('#credentialModal').modal('show');
}

// To enable link to tab
$(document).ready(function(){
	var hash = document.location.hash;
	//console.log(hash);
	$(".nav-item a[href=\\"+hash+"]").tab('show');
});

// Change hash for page-reload
$('.nav-tabs a').on('shown.bs.tab', function (e) {
	window.location.hash = e.target.hash;
});