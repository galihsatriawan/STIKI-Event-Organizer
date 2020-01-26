package id.ac.stiki.doleno.stikieventorganizer.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.ac.stiki.doleno.stikieventorganizer.di.event.EventNetworkModule
import id.ac.stiki.doleno.stikieventorganizer.di.event.EventPersistenceModule
import id.ac.stiki.doleno.stikieventorganizer.di.event.EventRepositoryModule
import id.ac.stiki.doleno.stikieventorganizer.di.event.detail.EventMainViewModelModule
import id.ac.stiki.doleno.stikieventorganizer.di.event.detail.ParticipantSelectionModule
import id.ac.stiki.doleno.stikieventorganizer.di.event.detail.ParticipantSelectionViewModelModule
import id.ac.stiki.doleno.stikieventorganizer.di.event.scanner.ScannerModule
import id.ac.stiki.doleno.stikieventorganizer.di.event.scanner.ScannerViewModelModule
import id.ac.stiki.doleno.stikieventorganizer.di.invitation.InvitationNetworkModule
import id.ac.stiki.doleno.stikieventorganizer.di.invitation.InvitationPersistenceModule
import id.ac.stiki.doleno.stikieventorganizer.di.invitation.InvitationRepositoryModule
import id.ac.stiki.doleno.stikieventorganizer.di.invitation.detail.InvitationDetailModule
import id.ac.stiki.doleno.stikieventorganizer.di.invitation.detail.InvitationDetailViewModelModule
import id.ac.stiki.doleno.stikieventorganizer.di.participant.ParticipantNetworkModule
import id.ac.stiki.doleno.stikieventorganizer.di.participant.ParticipantPersistenceModule
import id.ac.stiki.doleno.stikieventorganizer.di.participant.ParticipantRepositoryModule
import id.ac.stiki.doleno.stikieventorganizer.di.participant.detail.ParticipantDetailModule
import id.ac.stiki.doleno.stikieventorganizer.di.participant.detail.ParticipantDetailViewModelModule
import id.ac.stiki.doleno.stikieventorganizer.di.user.UserNetworkModule
import id.ac.stiki.doleno.stikieventorganizer.di.user.UserPersistenceModule
import id.ac.stiki.doleno.stikieventorganizer.di.user.UserRepositoryModule
import id.ac.stiki.doleno.stikieventorganizer.di.user.login.LoginModule
import id.ac.stiki.doleno.stikieventorganizer.di.user.login.LoginViewModelModule
import id.ac.stiki.doleno.stikieventorganizer.di.user.register.RegisterModule
import id.ac.stiki.doleno.stikieventorganizer.di.user.register.RegisterViewModelModule
import id.ac.stiki.doleno.stikieventorganizer.ui.SplashScreen
import id.ac.stiki.doleno.stikieventorganizer.ui.invitations.detail.InvitationDetailActivity
import id.ac.stiki.doleno.stikieventorganizer.ui.myevents.detail.EventDetailActivity
import id.ac.stiki.doleno.stikieventorganizer.ui.myevents.detail.EventDetailMainViewModel
import id.ac.stiki.doleno.stikieventorganizer.ui.myevents.detail.ParticipantSelectionActivity
import id.ac.stiki.doleno.stikieventorganizer.ui.myevents.scanner.ScannerActivity
import id.ac.stiki.doleno.stikieventorganizer.ui.myparticipants.detail.ParticipantDetailActivity
import id.ac.stiki.doleno.stikieventorganizer.ui.user.login.LoginActivity
import id.ac.stiki.doleno.stikieventorganizer.ui.user.register.RegisterActivity


@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
        modules = [
            EventMainViewModelModule::class,
            EventNetworkModule::class,
            EventPersistenceModule::class,
            EventRepositoryModule::class,
            InvitationNetworkModule::class,
            InvitationPersistenceModule::class,
            InvitationRepositoryModule::class
        ]
    )
    abstract fun injectEventDetailActivity() : EventDetailActivity
    @ContributesAndroidInjector(
        modules = [
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
    abstract fun injectInvitationDetailActivity(): InvitationDetailActivity

    @ContributesAndroidInjector(
        modules = [
            ParticipantSelectionModule::class,
            ParticipantSelectionViewModelModule::class,
            InvitationNetworkModule::class,
            InvitationPersistenceModule::class,
            InvitationRepositoryModule::class
        ]
    )
    abstract fun injectParticipantSelectionActivity(): ParticipantSelectionActivity

    @ContributesAndroidInjector(
        modules = [
            ScannerModule::class,
            ScannerViewModelModule::class,
            InvitationNetworkModule::class,
            InvitationPersistenceModule::class,
            InvitationRepositoryModule::class
        ]
    )
    abstract fun injectScannerActivity(): ScannerActivity

    @ContributesAndroidInjector(
        modules = [
            ParticipantDetailModule::class,
            ParticipantDetailViewModelModule::class,
            ParticipantNetworkModule::class,
            ParticipantPersistenceModule::class,
            ParticipantRepositoryModule::class
        ]
    )
    abstract fun injectParticipantDetailActivity(): ParticipantDetailActivity

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
    abstract fun injectSplashScreenActivity(): SplashScreen
}