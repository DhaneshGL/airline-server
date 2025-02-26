from django.urls import path
from . import views

urlpatterns = [
    path('signup/', views.SignUpView.as_view(), name='signup'),
    path('login/', views.LoginView.as_view(), name='login'), 
    path('delete/<int:pk>/', views.UserDeleteView.as_view(), name='delete_user'),
]