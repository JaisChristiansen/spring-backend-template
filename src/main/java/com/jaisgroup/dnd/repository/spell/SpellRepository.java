package com.jaisgroup.dnd.repository.spell;

import com.jaisgroup.dnd.model.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpellRepository extends JpaRepository<Spell, UUID>, SpellRepositoryCustom {

}
