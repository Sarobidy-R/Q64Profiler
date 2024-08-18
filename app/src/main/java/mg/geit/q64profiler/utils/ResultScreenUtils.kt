package mg.geit.q64profiler.utils

import mg.geit.q64profiler.R

fun getProfileImageResource(profile: String): Int {
    return when (profile) {
        "bienfaiteur" -> R.drawable.bienfaiteur
        "insoumis" -> R.drawable.insoumis
        "visionnaire" -> R.drawable.visionnaire
        "célébrité" -> R.drawable.celebrite
        "médiateur" -> R.drawable.mediateur
        "conservateur" -> R.drawable.conservateur
        "épicurien" -> R.drawable.epicurien
        else -> R.drawable.leader
    }
}

fun getProfileDescription(profile: String): String {
    return when (profile) {
        "bienfaiteur" -> "Le Bienfaiteur est une personne profondément engagée dans le soutien et l'amélioration du bien-être des autres. Animé par une compassion sincère, il consacre du temps, des ressources ou des efforts pour aider ceux qui en ont besoin. Son attitude bienveillante et sa générosité font de lui un pilier de la communauté, souvent impliqué dans des œuvres de charité ou des projets de solidarité. Le Bienfaiteur agit avec empathie, cherchant toujours à avoir un impact positif sur les vies qu'il touche."
        "insoumis" -> "L'Insoumis est une personne qui refuse de se conformer aux normes établies et défie les conventions sociales ou les règles imposées. Son esprit rebelle et son attitude non conformiste en font un individu qui privilégie l'indépendance et la liberté personnelle. L'Insoumis cherche à remettre en question le statu quo et à explorer des chemins alternatifs, souvent au prix de confrontations avec l'autorité ou les attentes sociétales."
        "visionnaire" -> "Le Visionnaire est une personne dotée d'une capacité exceptionnelle à anticiper l'avenir et à imaginer des possibilités inédites. Sa pensée créative et sa perspective originale lui permettent de concevoir des idées et des solutions innovantes. Le Visionnaire est souvent en avance sur son temps, guidant les autres vers des horizons nouveaux grâce à sa capacité à voir au-delà des tendances actuelles et à imaginer des évolutions audacieuses."
        "célébrité" -> "La Célébrité est un individu reconnu et souvent admiré par le public, grâce à ses réalisations dans des domaines variés tels que le divertissement, le sport ou les affaires. Sa notoriété lui confère une grande visibilité et influence, et il peut souvent être l'objet d'une attention médiatique constante. La Célébrité possède une aura charismatique et est souvent impliquée dans des activités publiques, des événements médiatiques et des projets de grande envergure."
        "médiateur" -> "Le Médiateur est un expert en gestion des conflits et en communication, capable de naviguer entre différents points de vue pour trouver des solutions harmonieuses. Doté d'une grande diplomatie et d'une écoute attentive, il est souvent sollicité pour résoudre des disputes ou faciliter des négociations. Le Médiateur excelle dans l'art de créer des ponts entre les parties opposées et de parvenir à des accords mutuellement satisfaisants."
        "conservateur" -> "Le Conservateur est une personne qui valorise les traditions, les valeurs établies et la stabilité. Il est souvent attaché à préserver les structures et les pratiques héritées du passé, en cherchant à maintenir l'ordre et à éviter les changements brusques. Le Conservateur privilégie la continuité et la prudence, en mettant l'accent sur la préservation de ce qui est éprouvé et fiable, souvent en s'opposant aux réformes radicales ou aux innovations disruptives."
        "épicurien" -> "L'Épicurien est une personne qui valorise les plaisirs de la vie, qu'ils soient gastronomiques, culturels ou sensoriels. Il recherche le bonheur à travers les expériences de la vie quotidienne et s'efforce de savourer chaque moment avec intensité. L'Épicurien est souvent passionné par la gastronomie, les arts et les voyages, et il apprécie les plaisirs raffinés tout en cultivant une approche décontractée et joyeuse de l'existence."
        else -> "Le Leader est une personne qui inspire et guide les autres grâce à son charisme, sa vision claire et sa capacité à prendre des décisions. Il possède des compétences en gestion et en motivation, et il est capable de rassembler une équipe autour d'un objectif commun. Le Leader est souvent reconnu pour son influence positive et son aptitude à diriger avec confiance, à encourager l'innovation et à mobiliser les ressources pour atteindre des buts ambitieux."
    }
}