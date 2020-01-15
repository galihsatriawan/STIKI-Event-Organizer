package id.shobrun.stikieventorganizer.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.shobrun.stikieventorganizer.di.event.EventNetworkModule
import id.shobrun.stikieventorganizer.di.event.EventPersistenceModule
import id.shobrun.stikieventorganizer.di.event.EventRepositoryModule
import id.shobrun.stikieventorganizer.di.event.detail.*
import id.shobrun.stikieventorganizer.di.invitation.InvitationNetworkModule
import id.shobrun.stikieventorganizer.di.invitation.InvitationPersistenceModule
import id.shobrun.stikieventorganizer.di.invitation.InvitationRepositoryModule
import id.shobrun.stikieventorganizer.di.invitation.detail.InvitationDetailModule
import id.shobrun.stikieventorganizer.di.invitation.detail.InvitationDetailViewModelModule
import id.shobrun.stikieventorganizer.di.participant.ParticipantNetworkModule
import id.shobrun.stikieventorganizer.di.participant.ParticipantPersistenceModule
import id.shobrun.stikieventorganizer.di.participant.ParticipantRepositoryModule
import id.shobrun.stikieventorganizer.di.participant.detail.ParticipantDetailModule
import id.shobrun.stikieventorganizer.di.participant.detail.ParticipantDetailViewModelModule
import id.shobrun.stikieventorganizer.di.user.UserNetworkModule
import id.shobrun.stikieventorganizer.di.user.UserPersistenceModule
import id.shobrun.stikieventorganizer.di.user.UserRepositoryModule
import id.shobrun.stikieventorganizer.di.user.login.LoginModule
import id.shobrun.stikieventorganizer.di.user.login.LoginViewModelModule
import id.shobrun.stikieventorganizer.di.user.register.RegisterModule
import id.shobrun.stikieventorganizer.di.user.register.RegisterViewModelModule
import id.shobrun.stikieventorganizer.models.entity.Participant
import id.shobrun.stikieventorganizer.ui.SplashScreen
import id.shobrun.stikieventorganizer.ui.invitations.detail.InvitationDetailActivity
import id.shobrun.stikieventorganizer.ui.invitations.detail.InvitationDetailViewModel
import id.shobrun.stikieventorganizer.ui.myevents.detail.EventDetailActivity
import id.shobrun.stikieventorganizer.ui.myevents.detail.EventDetailFragment
import id.shobrun.stikieventorganizer.ui.myevents.detail.ParticipantSelectionActivity
import id.shobrun.stikieventorganizer.ui.myparticipants.detail.ParticipantDetailActivity
import id.shobrun.stikieventorganizer.ui.myparticipants.detail.ParticipantDetailViewModel
import id.shobrun.stikieventorganizer.ui.user.login.LoginActivity
import id.shobrun.stikieventorganizer.ui.user.register.RegisterActivity


@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
        modules =[
            InvitationDetailModule::class,
            InvitationDetailViewModelModule::class,
            InvitationNetworkModule::class,
            InvitationPersistenceModule::class,
            InvitationRepositoryModule::class,
            EventNetworkModule::class,
            EventPersistenceModule::class,
            EventRepositoryModule::class
        ]
    )
    abstract fun injectInvitationDetailActivity() : InvitationDetailActivity
    @ContributesAndroidInjector(
        modules =[
            ParticipantSelectionModule::class,
            ParticipantSelectionViewModelModule::class,
            InvitationNetworkModule::class,
            InvitationPersistenceModule::class,
            InvitationRepositoryModule::class
        ]
    )
    abstract fun injectParticipantSelectionActivity() : ParticipantSelectionActivity

    @ContributesAndroidInjector(
        modules = [
            ParticipantDetailModule::class,
            ParticipantDetailViewModelModule::class,
            ParticipantNetworkModule::class,
            ParticipantPersistenceModule::class,
            ParticipantRepositoryModule::class
        ]
    )
    abstract fun injectParticipantDetailActivity() : ParticipantDetailActivity

    @ContributesAndroidInjector(
        modules = [
            LoginModule::class,
            LoginViewModelModule::class,
            UserNetworkModule::class,
            UserPersistenceModule::class,
            UserRepositoryModule::class
        ]
    )
    abstract fun injectLoginActivity(): LoginActivity

    @ContributesAndroidInjector(
        modules = [
            RegisterModule::class,
            RegisterViewModelModule::class,
            UserNetworkModule::class,
            UserPersistenceModule::class,
            UserRepositoryModule::class
        ]
    )
    abstract fun injectRegisterActivity(): RegisterActivity

    @ContributesAndroidInjector
    abstract fun injectSplashScreenActivity() : SplashScreen
}