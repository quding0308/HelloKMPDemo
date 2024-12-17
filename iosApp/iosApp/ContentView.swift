import UIKit
import SwiftUI
import shared

struct ContentView: View {
//	let greet = Greeting().greet()
    
    
    let kotlinObj = MyKotlinClass11()
    
	var body: some View {
        ComposeLayer()
	}
}

struct ComposeLayer: View {
    var body: some View {
        ComposeViewControllerInSwiftUI().ignoresSafeArea(.keyboard)
    }
}

struct ComposeViewControllerInSwiftUI: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        return Main_iosKt.ComposeEntryPoint()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}

//struct ContentView_Previews: PreviewProvider {
//	static var previews: some View {
//		ContentView()
//	}
//}
